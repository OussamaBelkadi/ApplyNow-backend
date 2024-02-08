package example.brief.MyRh.controller;

import example.brief.MyRh.dtos.CandidateDTO;
import example.brief.MyRh.dtos.test.ResponseRegisterTest;
import example.brief.MyRh.services.SpecialityService;
import example.brief.MyRh.services.TestService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BadgeController {
    final TestService testService;

    public BadgeController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseRegisterTest> registerToTest(@RequestBody CandidateDTO candidateDTO){
        this.testService.registerToTest(candidateDTO);
        return null;
    }
}
