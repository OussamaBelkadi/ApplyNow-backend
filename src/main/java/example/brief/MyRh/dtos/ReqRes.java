package example.brief.MyRh.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReqRes {

    private Long id;
    private String email;
    private String password;
    private String Token;
    private String ExpirationTime;
    private String RefreshToken;
    private String role;


}
