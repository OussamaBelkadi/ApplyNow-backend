package example.brief.MyRh.controller;


import example.brief.MyRh.dtos.CandiatDto;
import example.brief.MyRh.services.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "candidat")
public class CandidatController {

    private final CandidatService candidatService;

    @Autowired
    public CandidatController(CandidatService candidatService) {
        this.candidatService = candidatService;
    }

    @PostMapping( "")
    public ResponseEntity registercandidat(@RequestBody  CandiatDto candiatDto){

        return ResponseEntity.ok(candidatService.registerCandiat(candiatDto));

    }



}
