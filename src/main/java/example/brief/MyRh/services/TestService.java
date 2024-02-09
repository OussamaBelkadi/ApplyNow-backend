package example.brief.MyRh.services;

import example.brief.MyRh.dtos.CandidateDTO;
import example.brief.MyRh.dtos.QuestionDto;
import example.brief.MyRh.dtos.test.RequestRecord;
import example.brief.MyRh.dtos.test.ResponseRegisterTest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TestService {
    List<QuestionDto> registerToTest(CandidateDTO candidateDTO);
    boolean recordTest(RequestRecord requestRecord);
    List<QuestionDto> fetchQuestionSpeciality(Long SpecialityID);

}
