package example.brief.MyRh.services;

import example.brief.MyRh.dtos.CandidateDTO;
import example.brief.MyRh.dtos.test.ResponseRegisterTest;
import org.springframework.stereotype.Service;

public interface TestService {
    ResponseRegisterTest registerToTest(CandidateDTO candidateDTO);
}
