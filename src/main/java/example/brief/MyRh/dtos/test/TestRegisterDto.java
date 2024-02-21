package example.brief.MyRh.dtos.test;

import example.brief.MyRh.dtos.QuestionDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class TestRegisterDto {
    List<QuestionDto> questionList;
    Long testId;
    Long candidateId;

}
