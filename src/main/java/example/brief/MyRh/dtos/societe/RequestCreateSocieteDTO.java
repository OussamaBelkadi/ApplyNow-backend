package example.brief.MyRh.dtos.societe;

import example.brief.MyRh.Enum.UserRoles;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class RequestCreateSocieteDTO {
    @Nullable
    private MultipartFile imageFile;
    private String email;
    private String password;
    private String adresse;
    private String phone;
    private UserRoles roles;
    private String userName;

}
