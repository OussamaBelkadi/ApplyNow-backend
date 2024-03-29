package example.brief.MyRh.controller;

import example.brief.MyRh.dtos.CandidateDTO;
import example.brief.MyRh.dtos.QuestionDto;
import example.brief.MyRh.dtos.test.RequestRecord;
import example.brief.MyRh.dtos.test.ResponseRegisterTest;
import example.brief.MyRh.dtos.test.TestRegisterDto;
import example.brief.MyRh.exceptions.exception.ExpiredAttempt;
import example.brief.MyRh.services.SpecialityService;
import example.brief.MyRh.services.TestService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BadgeController {
    final TestService testService;

    public BadgeController(TestService testService) {
        this.testService = testService;
    }



    @PostMapping("/register")
    public ResponseEntity<TestRegisterDto> registerToTest(@RequestBody CandidateDTO candidateDTO){
        return ResponseEntity.ok(this.testService.registerToTest(candidateDTO));
    }

    @PostMapping("/test")
    public ResponseEntity<String> recordTest(@RequestBody  RequestRecord requestRecord){

        boolean result = this.testService.recordTest(requestRecord);
        if (result) return ResponseEntity.status(HttpStatus.OK).body("Congratulation you have pass the test");
        else throw new ExpiredAttempt("you didn't pass the test, next time");
    }


}
