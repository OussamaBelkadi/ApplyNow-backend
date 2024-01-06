package example.brief.MyRh.controller;

import example.brief.MyRh.dtos.PostuleDto;
import example.brief.MyRh.dtos.offre.request.RequestPostuleOffre;
import example.brief.MyRh.entities.Offre;
import example.brief.MyRh.repositories.OffreRepository;
import example.brief.MyRh.services.PostuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("postule")
public class PostuleController {


    private final PostuleService postuleService;


    @Autowired
    public PostuleController(PostuleService postuleService, OffreRepository offreRepository) {
        this.postuleService = postuleService;
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> postuleOffre(   @RequestParam("offreId") Long offreId,
                                                  @RequestParam("nom_complet") String nomComplet,
                                                  @RequestParam("tel") int tel,
                                                  @RequestParam("cv") MultipartFile cv,
                                                  @RequestParam("motivation") MultipartFile motivation){
        RequestPostuleOffre requestPostuleOffre = RequestPostuleOffre.builder().offreId(offreId)
                .cv(cv)
                .motivation(motivation)
                .nom_complet(nomComplet)
                .tel(tel)
                .build();
        this.postuleService.potuleOffre(requestPostuleOffre);
        return ResponseEntity.status(HttpStatus.OK).body("Postuler avec success");
    }

    @GetMapping("/{offerId}")
    public ResponseEntity fetchPostuleOffers(@PathVariable Long offerId){

        return ResponseEntity.ok(postuleService.findPostuleOffers(offerId));

    }

    @GetMapping("/cv")
    public ResponseEntity fetchCv(@RequestParam String cvPath) throws IOException {
        Path filePath = Paths.get(cvPath);
        byte[] fileContent = Files.readAllBytes(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);

    }
    @GetMapping("/mt")
    public ResponseEntity fetchmt(@RequestParam String mtPath) throws IOException {
        Path filePath = Paths.get(mtPath);
        byte[] fileContent = Files.readAllBytes(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);

    }

    /*@GetMapping("/{offerId}")
    public ResponseEntity fetchPostuleOffers(@PathVariable Long offerId) throws IOException {
        List<PostuleDto> postule = postuleService.findPostuleOffers(offerId);
        Path filePath = Paths.get(postule.get(0).getCv());
        byte[] fileContent = Files.readAllBytes(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);


        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        //return ResponseEntity.ok(postuleService.findPostuleOffers(offerId));

    }*/



}
