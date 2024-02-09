package example.brief.MyRh.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionDto {

    private Long specialityID;
    private Long id;
    private String qst;

    private List<ResponseDto> responseDtoList;

}