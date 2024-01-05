package example.brief.MyRh.services.serviceImpl;

import example.brief.MyRh.Enum.CompteStatus;
import example.brief.MyRh.Util.EmailSender;
import example.brief.MyRh.dtos.SocieteDTO;
import example.brief.MyRh.dtos.societe.RequestCreateSocieteDTO;
import example.brief.MyRh.entities.Societe;
import example.brief.MyRh.exceptions.exception.LoginSocieteException;
import example.brief.MyRh.exceptions.exception.NotExist;
import example.brief.MyRh.mappers.SocieteMapper;
import example.brief.MyRh.repositories.SocieteRepository;
import example.brief.MyRh.services.SocieteService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
public class SocieteServiceImpl implements SocieteService {
    private SocieteRepository societeRepository;
    private SocieteMapper societeMapper;
    @Value("${UPLOAD_DIR.Images}")
    private static final String UPLOAD_DIR = "src/main/resources/images/";
    private final EmailSender emailSender;
    @Autowired
    public SocieteServiceImpl(SocieteRepository societeRepository, EmailSender emailSender) {
        this.societeRepository = societeRepository;
        this.emailSender = emailSender;
        this.societeMapper = SocieteMapper.INSTANCE;
    }


    @Override
    public SocieteDTO createSociete(RequestCreateSocieteDTO createSocieteDTO) throws IOException {

        Societe societe = Societe.builder()
                .email(createSocieteDTO.getEmail())
                .password(createSocieteDTO.getPassword())
                .adresse(createSocieteDTO.getAdresse())
                .build();
        String hashPassword = BCrypt.hashpw(societe.getPassword(), BCrypt.gensalt());
        if (!hashPassword.isEmpty()) {
                MultipartFile file = createSocieteDTO.getImageFile();
            if (file != null && !file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    Path path = Paths.get("src/main/resources/images/" + UUID.randomUUID().toString()+file.getOriginalFilename());
                    Files.write(path, bytes);
                    societe.setImage(path.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String Code = UUID.randomUUID().toString();
            societe.setCode(Code);
            societe = societeRepository.save(societe);
            emailSender.sendEmail(createSocieteDTO.getEmail(),"code verification", Code);
            return societeMapper.toDto(societe);
        } else {
            throw new LoginSocieteException("the societe field to create");
        }
    }

    @Override
    public SocieteDTO loginSociete(SocieteDTO societeDTO) {

        Societe societe = societeMapper.toEntity(societeDTO);
        Optional<Societe> societeOpt = societeRepository.findByEmail(societe.getEmail());
        if (societeOpt.isPresent()){
            if(BCrypt.checkpw(societe.getPassword(), societeOpt.get().getPassword())){
                societe = societeOpt.get();
            }else{
                throw  new LoginSocieteException("the password is not correct");
            }
            return societeMapper.toDto(societe);
        }else {
            throw  new LoginSocieteException("the login operation is field");
        }

    }

    @Override
    public Boolean verificationCode(String code) {
        Societe societe = this.societeRepository.findByCode(code).orElseThrow(() -> new NotExist("This code " + code + "is not valide "));
        societe.setStatus(CompteStatus.VALID);
        this.societeRepository.save(societe);
        return true;
    }
}
