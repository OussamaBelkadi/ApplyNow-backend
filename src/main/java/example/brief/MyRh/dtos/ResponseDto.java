package example.brief.MyRh.dtos;

import example.brief.MyRh.entities.Question;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {

    private int responseNumber;

    private Long id;
    private  String resoponse;
    private boolean status;
    private Long questionId;

}
