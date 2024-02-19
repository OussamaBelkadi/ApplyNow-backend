package example.brief.MyRh.services;

import example.brief.MyRh.dtos.CandidateDTO;
import example.brief.MyRh.dtos.QuestionDto;
import example.brief.MyRh.dtos.test.RequestRecord;
import example.brief.MyRh.dtos.test.ResponseRegisterTest;
import example.brief.MyRh.dtos.test.TestRegisterDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TestService {
    TestRegisterDto registerToTest(CandidateDTO candidateDTO);
    boolean recordTest(RequestRecord requestRecord);
    List<QuestionDto> fetchQuestionSpeciality(Long SpecialityID);

}
