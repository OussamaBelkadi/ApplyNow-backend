package example.brief.MyRh.dtos;

import example.brief.MyRh.Enum.ConnectedStatus;
import example.brief.MyRh.Enum.StatusOffre;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostuleDto {
    private int id;
    private CandidateDTO candidateDTO;
    private String cv;
    private int tel;
    private String motivation;
    private ConnectedStatus postuleStatus;
    private StatusOffre postuleResponse;
}
