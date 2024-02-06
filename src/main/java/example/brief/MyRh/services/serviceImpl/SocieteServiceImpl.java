package example.brief.MyRh.services.serviceImpl;

import com.stripe.exception.StripeException;
import example.brief.MyRh.Enum.CompteStatus;
import example.brief.MyRh.Enum.ConnectedStatus;
import example.brief.MyRh.Enum.SubscriptionStatus;
import example.brief.MyRh.Util.EmailSender;
import example.brief.MyRh.dtos.CompanySubscribeResponse;
import example.brief.MyRh.dtos.SocieteDTO;
import example.brief.MyRh.dtos.societe.RequestCreateSocieteDTO;
import example.brief.MyRh.entities.Postule;
import example.brief.MyRh.entities.Societe;
import example.brief.MyRh.exceptions.exception.BadRequestException;
import example.brief.MyRh.exceptions.exception.LoginSocieteException;
import example.brief.MyRh.exceptions.exception.NotExist;
import example.brief.MyRh.mappers.SocieteMapper;
import example.brief.MyRh.repositories.SocieteRepository;
import example.brief.MyRh.services.CompanySubscriptionService;
import example.brief.MyRh.services.PaymentService;
import example.brief.MyRh.services.SocieteService;
import jakarta.persistence.EntityNotFoundException;
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
public class SocieteServiceImpl implements SocieteService, CompanySubscriptionService {
    private SocieteRepository societeRepository;
    private SocieteMapper societeMapper;
    @Value("${UPLOAD_DIR.Images}")
    private static final String UPLOAD_DIR = "src/main/resources/images/";
    private final EmailSender emailSender;
    private PaymentService paymentService;
    @Autowired
    public SocieteServiceImpl(SocieteRepository societeRepository, EmailSender emailSender, PaymentService paymentService) {
        this.societeRepository = societeRepository;
        this.emailSender = emailSender;
        this.paymentService = paymentService;
        this.societeMapper = SocieteMapper.INSTANCE;
    }


    @Override
    public SocieteDTO createSociete(RequestCreateSocieteDTO createSocieteDTO) throws IOException {

        Societe societe = Societe.builder()
                .email(createSocieteDTO.getEmail())
                .password(createSocieteDTO.getPassword())
                .adresse(createSocieteDTO.getAdresse())
                .phone(Integer.parseInt(createSocieteDTO.getPhone()))
                .build();

        System.out.println("the email " + societe.getEmail() + " the phone number " + societe.getPhone());
        String hashPassword = BCrypt.hashpw(societe.getPassword(), BCrypt.gensalt());
        System.out.println("the hashed password " + hashPassword);
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
            societe.setPassword(hashPassword);
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

                societeOpt.get().setConnected(ConnectedStatus.CONNECTED);
                societe = societeRepository.save(societeOpt.get());


            return societeMapper.toDto(societe);
            }else{
            throw  new LoginSocieteException("the password is not correct");
            }
        }else {
            throw  new LoginSocieteException("the login operation is failed");
        }

    }

    @Override
    public Boolean verificationCode(String code) {
        Societe societe = this.societeRepository.findByCode(code).orElseThrow(() -> new NotExist("This code " + code + "is not valide "));
        societe.setStatus(CompteStatus.VALID);
        this.societeRepository.save(societe);
        return true;
    }

    @Override
    public Boolean verificationSubscription(Long companyId, String subscriptionStatus) {
        boolean result = false;
        Societe company = this.societeRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company Not Found"));
        company.setSubscription(SubscriptionStatus.valueOf(subscriptionStatus));
        this.societeRepository.save(company);
        result = true;
        return result;
    }



}
