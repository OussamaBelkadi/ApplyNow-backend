package example.brief.MyRh.dtos;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ResponseTest {
    String nameSpeciality;
    List<TypePatternQuestions.Question> questions = new ArrayList<>();
}
