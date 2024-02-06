package example.brief.MyRh.services.serviceImpl;

import example.brief.MyRh.Enum.ConnectedStatus;
import example.brief.MyRh.Enum.Grade;
import example.brief.MyRh.Enum.StatusOffre;
import example.brief.MyRh.dtos.PostuleDto;
import example.brief.MyRh.dtos.offre.request.RequestPostuleOffre;
import example.brief.MyRh.entities.Candidate;

import example.brief.MyRh.entities.Offre;
import example.brief.MyRh.entities.Postule;
import example.brief.MyRh.entities.Societe;
import example.brief.MyRh.exceptions.exception.AccessOffreException;
import example.brief.MyRh.exceptions.exception.NotExist;
import example.brief.MyRh.exceptions.exception.OffreCreateException;
import example.brief.MyRh.mappers.PostuleMapper;
import example.brief.MyRh.repositories.CondidateRepository;

import example.brief.MyRh.repositories.OffreRepository;
import example.brief.MyRh.repositories.PostuleRepository;
import example.brief.MyRh.repositories.SocieteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import example.brief.MyRh.services.PostuleService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class PostuleServiceImpl implements PostuleService {
    private final PostuleRepository postuleRepository;
    private final OffreRepository offreRepository;
    private final SocieteRepository societeRepository;
    private final CondidateRepository condidateRepository;

    private final PostuleMapper postuleMapper;
    @Value("${UPLOAD_DIR.Cv}")
    private String pathCv;
    @Autowired
    public PostuleServiceImpl(PostuleRepository postuleRepository, OffreRepository offreRepository, SocieteRepository societeRepository, CondidateRepository condidateRepository) {

        this.postuleRepository = postuleRepository;
        this.offreRepository = offreRepository;
        this.societeRepository = societeRepository;
        this.condidateRepository = condidateRepository;
        this.postuleMapper = PostuleMapper.INSTANCE;
    }

    @Override
    public PostuleDto potuleOffre(RequestPostuleOffre requestPostuleOffre) {

        Long id = requestPostuleOffre.getOffreId();
        Offre offre = offreRepository.findById(id).orElseThrow(OffreCreateException::new);
        Candidate candidate = this.condidateRepository.findById(requestPostuleOffre.getCandidateId()).orElseThrow(()->new NotExist("the candidate not exist"));
        Postule postule = Postule.builder()
                .candidate(candidate).build();
        System.out.println(candidate.getNbrPostule());
        if(offre.getStatus().equals(StatusOffre.ACCEPTED)){
            if (candidate.getNbrPostule()<3 && candidate.getGrade().equals(Grade.Standard)) {
                if (checkPostuleState(requestPostuleOffre.getSocieteId())) {
                    postule.setOffre(offre);
                    postule.setPostuleStatus(ConnectedStatus.CONNECTED);
                    MultipartFile fileCv = requestPostuleOffre.getCv();
                    if (fileCv != null && !fileCv.isEmpty()) {
                        String pathCV = this.registerCv(fileCv);
                        postule.setCv(pathCV);
                    }
                    MultipartFile fileMotivation = requestPostuleOffre.getMotivation();
                    if (fileMotivation != null && !fileMotivation.isEmpty()) {
                        String pathMotivation = this.registerMotivation(fileMotivation);
                        postule.setMotivation(pathMotivation);
                    }
                    candidate.setNbrPostule(candidate.getNbrPostule()+1);
                    this.condidateRepository.save(candidate);
                    postule = postuleRepository.save(postule);
                } else {
                    postule.setOffre(offre);
                    MultipartFile fileCv = requestPostuleOffre.getCv();
                    if (fileCv != null && !fileCv.isEmpty()) {
                        String pathCV = this.registerCv(fileCv);
                        postule.setCv(pathCV);
                    }
                    MultipartFile fileMotivation = requestPostuleOffre.getMotivation();
                    if (fileMotivation != null && !fileMotivation.isEmpty()) {
                        String pathMotivation = this.registerMotivation(fileMotivation);
                        postule.setMotivation(pathMotivation);
                    }
                    candidate.setNbrPostule(candidate.getNbrPostule()+1);
                    this.condidateRepository.save(candidate);
                    postule = postuleRepository.save(postule);
                }
                return postuleMapper.toDto(postule);
            }else throw new NotExist("you have to pay");

        }else throw new AccessOffreException("the offre don't by accessed");

    }

    @Override
    public List<PostuleDto> findPostuleOffers(Long offereId) {

        Offre offre =   offreRepository.findById(offereId).orElseThrow(()->new NotExist("offre doest exist"));
        List<Postule> postules = postuleRepository.findAllByOffre(offre);
        List<PostuleDto> postuleDtos = new ArrayList<>();
        for (Postule P : postules){
            PostuleDto postuleDto = PostuleMapper.INSTANCE.toDto(P);
            postuleDtos.add(postuleDto);
        }
        return postuleDtos;

    }

    //find postule By status
    @Override
    public List<PostuleDto> FindPostuleByStatus(Long offerId, String Status) {
        Offre offre =   offreRepository.findById(offerId).orElseThrow(()->new NotExist("offre doest exist"));
        List<Postule> postules = postuleRepository.findAllByOffreAndPostuleStatus(offre, ConnectedStatus.valueOf(Status));

        List<PostuleDto> postuleDtos = new ArrayList<>();

        for (Postule P : postules){
            PostuleDto postuleDto = PostuleMapper.INSTANCE.toDto(P);
            postuleDtos.add(postuleDto);
        }
        return postuleDtos;
    }

    @Override
    public List<PostuleDto> findPostuleByTitre(String Titre) {

        List<Offre> offres =   offreRepository.findAllByTitre(Titre);
        List<Postule> postules = new ArrayList<>();
        List<PostuleDto> postuleDtos = new ArrayList<>();

        for (Offre Of : offres){

            postules = postuleRepository.findAllByOffre(Of);


        }
        for (Postule P : postules){
            PostuleDto postuleDto = PostuleMapper.INSTANCE.toDto(P);
            postuleDtos.add(postuleDto);
        }

        return postuleDtos;

    }

    @Override
    public HashMap<String, Long> OfferStatistics(Long offerId) {
        HashMap<String,Long> statistics = new HashMap<>();
        Long acceptedApplication =  postuleRepository.statistics(StatusOffre.ACCEPTED ,offerId);
        Long RefusedApplication =  postuleRepository.statistics(StatusOffre.REJECTED ,offerId);
        Long WaitedApplication =  postuleRepository.statistics(StatusOffre.REJECTED ,offerId);

        statistics.put("accepted",acceptedApplication);
        statistics.put("refused",RefusedApplication);
        statistics.put("pending",WaitedApplication);


        return statistics;
    }

    @Override
    public Boolean ValiderCandidature(Long postuleId, String Status) {

        Optional<Postule> postule = Optional.ofNullable(postuleRepository.findById(postuleId).orElseThrow(() -> new NotExist("doesnt exist")));
        postule.get().setPostuleResponse(StatusOffre.valueOf(Status));
        postuleRepository.save(postule.get());
        return true;

    }


    private boolean checkPostuleState(long societeId) {

        boolean result = false;
        Societe societe = this.societeRepository.findById(societeId).orElseThrow(()-> new NotExist("the societe dont exist"));
        if (societe.getConnected().equals(ConnectedStatus.CONNECTED)){
            result = true;
        }
        System.out.println(result);
        return  result;

    }
    private String registerCv(MultipartFile fileCv){
        String pathDB = null;
            try {
                byte[] bytes = fileCv.getBytes();
                Path path = Paths.get("src/main/resources/cv/" + UUID.randomUUID().toString() + fileCv.getOriginalFilename());
                Files.write(path, bytes);
                pathDB =  path.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return pathDB;
    }

    private String registerMotivation(MultipartFile fileMotivation){
        String pathDB = null;
        try {
            byte[] bytes = fileMotivation.getBytes();
            Path path = Paths.get("src/main/resources/motivation/" + UUID.randomUUID().toString() + fileMotivation.getOriginalFilename());
            Files.write(path, bytes);
            pathDB = path.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathDB;
    }



}
