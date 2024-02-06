package example.brief.MyRh.controller;

import example.brief.MyRh.dtos.SocieteDTO;
import example.brief.MyRh.dtos.societe.RequestCreateSocieteDTO;
import example.brief.MyRh.services.OffreService;
import example.brief.MyRh.services.SocieteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("societes")
public class SocieteController {
    private SocieteService societeService;

    private final OffreService offreService;

    @Autowired
    public SocieteController(SocieteService societeService, OffreService offreService) {
        this.societeService = societeService;
        this.offreService = offreService;
    }

    @PostMapping("/register")
    public ResponseEntity<SocieteDTO> createNewSociete(RequestCreateSocieteDTO societeDTO) throws IOException {
        return  ResponseEntity.status(HttpStatus.CREATED).body(this.societeService.createSociete(societeDTO));
    }
    @PostMapping("/login")
    public ResponseEntity loginSociete(@RequestBody  SocieteDTO societeDTO){
        return ResponseEntity.ok(this.societeService.loginSociete(societeDTO));
    }


    @PostMapping("/validation/{code}")
    @PreAuthorize("hasAuthority('Role_Agent')")
    public ResponseEntity<String> validationCompteSociete(@PathVariable String code){

        System.out.println("code " + code);
        boolean result = this.societeService.verificationCode(code);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("Validation realized successfully");
        }else return ResponseEntity.status(HttpStatus.CONFLICT).body("Field validation");

    }

    @GetMapping("/offers/{societeId}")
    @PreAuthorize("hasAuthority('Role_Societe')")
    public ResponseEntity Offers(@PathVariable Long societeId){

        return ResponseEntity.ok(offreService.FetchSocieteOffres(societeId));

    }



}
