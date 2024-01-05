package example.brief.MyRh.controller;

import example.brief.MyRh.dtos.SocieteDTO;
import example.brief.MyRh.dtos.societe.RequestCreateSocieteDTO;
import example.brief.MyRh.services.SocieteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("societes")
public class SocieteController {
    private SocieteService societeService;

    @Autowired
    public SocieteController(SocieteService societeService) {
        this.societeService = societeService;
    }

    @PostMapping("/register")
    public ResponseEntity<SocieteDTO> createNewSociete(RequestCreateSocieteDTO societeDTO) throws IOException {
        return  ResponseEntity.status(HttpStatus.CREATED).body(this.societeService.createSociete(societeDTO));
    }
    @PostMapping("/login")
    public ResponseEntity<SocieteDTO> loginSociete(SocieteDTO societeDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.societeService.loginSociete(societeDTO));
    }
    @PostMapping("/validation/{code}")
     public ResponseEntity<String> validationCompteSociete(@PathVariable String code){
        System.out.println("code " + code);
        boolean result = this.societeService.verificationCode(code);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("Validation realized successfully");
        }else return ResponseEntity.status(HttpStatus.CONFLICT).body("Field validation");

    }
}
