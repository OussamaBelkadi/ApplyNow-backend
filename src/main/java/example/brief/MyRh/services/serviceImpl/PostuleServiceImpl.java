package example.brief.MyRh.services.serviceImpl;

import example.brief.MyRh.Enum.StatusOffre;
import example.brief.MyRh.dtos.PostuleDto;
import example.brief.MyRh.dtos.offre.request.RequestPostuleOffre;
import example.brief.MyRh.entities.Offre;
import example.brief.MyRh.entities.Postule;
import example.brief.MyRh.exceptions.exception.AccessOffreException;
import example.brief.MyRh.exceptions.exception.OffreCreateException;
import example.brief.MyRh.mappers.PostuleMapper;
import example.brief.MyRh.repositories.OffreRepository;
import example.brief.MyRh.repositories.PostuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import example.brief.MyRh.services.PostuleService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class PostuleServiceImpl implements PostuleService {
    private PostuleRepository postuleRepository;
    private OffreRepository offreRepository;
    private final PostuleMapper postuleMapper;
    @Value("${UPLOAD_DIR.Cv}")
    private String pathCv;
    @Autowired
    public PostuleServiceImpl(PostuleRepository postuleRepository, OffreRepository offreRepository) {
        this.postuleRepository = postuleRepository;
        this.offreRepository = offreRepository;
        this.postuleMapper = PostuleMapper.INSTANCE;
    }

    @Override
    public PostuleDto potuleOffre(RequestPostuleOffre requestPostuleOffre) {
        Long id = requestPostuleOffre.getOffreId();
        Offre offre = offreRepository.findById(id).orElseThrow(OffreCreateException::new);
        Postule postule = Postule.builder()
                .tel(requestPostuleOffre.getTel())
                .nom_complet("requestPostuleOffre.getNom_complet()")
                .build();
        if(offre.getStatus().equals(StatusOffre.ACCEPTED)){

             postule.setOffre(offre);
                 MultipartFile fileCv = requestPostuleOffre.getCv();
                 if (fileCv != null && !fileCv.isEmpty()) {
                     try {
                         byte[] bytes = fileCv.getBytes();
                         Path path = Paths.get("src/main/resources/cv/" + UUID.randomUUID().toString() + fileCv.getOriginalFilename());
                         Files.write(path, bytes);
                         postule.setCv(path.toString());
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }
             postule = postuleRepository.save(postule);
             return postuleMapper.toDto(postule);
        }else throw new AccessOffreException("the offre don't by accessed");
    }
}
