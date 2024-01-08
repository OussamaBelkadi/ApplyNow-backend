package example.brief.MyRh.controller;

import example.brief.MyRh.dtos.CandidateDTO;
import example.brief.MyRh.services.CandidateService;
import example.brief.MyRh.services.serviceImpl.CondidateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/candidate")
public class CandidateController {
    private CandidateService condidatService;
    @Autowired
    public CandidateController(CandidateService condidatService) {
        this.condidatService = condidatService;
    }

    @PostMapping("register")
    public ResponseEntity<String> registerCandidate(@RequestBody CandidateDTO condidatDTO) {

        boolean isUserRegistered = condidatService.registration(condidatDTO);

        if (isUserRegistered) {
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to register user");
        }
    }
    @PostMapping("login")
    public ResponseEntity<CandidateDTO> loginCandidate(@RequestBody CandidateDTO candidateDTO) {
//        candidateDTO = condidatService.login(candidateDTO);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(candidateDTO);

        return null;
    }
}
