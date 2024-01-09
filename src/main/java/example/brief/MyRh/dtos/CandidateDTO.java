package example.brief.MyRh.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CandidateDTO {
    private Long id;
    private String nom;
    private String email;
    private String password;
    private String connected;
}
