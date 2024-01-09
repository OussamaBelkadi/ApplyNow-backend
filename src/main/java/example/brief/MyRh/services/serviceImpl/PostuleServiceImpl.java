package example.brief.MyRh.services.serviceImpl;

import example.brief.MyRh.Enum.ConnectedStatus;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
                .tel(requestPostuleOffre.getTel())
                .nom_complet(requestPostuleOffre.getNom_complet())
                .candidate(candidate)
                .build();
        if(offre.getStatus().equals(StatusOffre.ACCEPTED)){
            if(checkPostuleState(requestPostuleOffre.getSocieteId())){
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
                postule = postuleRepository.save(postule);
            }else {
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
                postule = postuleRepository.save(postule);
            }
            return postuleMapper.toDto(postule);
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
    private boolean checkPostuleState(long societeId){
        boolean result = false;
        Societe societe = this.societeRepository.findById(societeId).orElseThrow(()-> new NotExist("the societe dont exist"));
        if (societe.getStatus().equals(ConnectedStatus.CONNECTED)){
            result = true;
        }
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
