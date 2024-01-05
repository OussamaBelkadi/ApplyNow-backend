package example.brief.MyRh.dtos.societe;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class RequestCreateSocieteDTO {
    private MultipartFile imageFile;
    private String email;
    private String password;
    private String adresse;

}
