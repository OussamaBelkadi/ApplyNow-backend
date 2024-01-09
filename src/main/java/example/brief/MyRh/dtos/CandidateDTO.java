package example.brief.MyRh.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CandidateDTO {
    private String fullname;
    private String email;
    private String password;
    private int tel;
    private String connected;
}
