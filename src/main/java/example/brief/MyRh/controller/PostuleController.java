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
                                                  @RequestParam("candidateId") int candidateId,
                                                  @RequestParam("cv") MultipartFile cv,
                                                  @RequestParam("motivation") MultipartFile motivation,
                                                  @RequestParam("id") Long idSociete
                                                 ){
        RequestPostuleOffre requestPostuleOffre = RequestPostuleOffre.builder()
                .offreId(offreId)
                .cv(cv)
                .motivation(motivation)
                .candidateId(candidateId)
                .societeId(idSociete)
                .build();
        this.postuleService.potuleOffre(requestPostuleOffre);
        return ResponseEntity.status(HttpStatus.OK).body("Postuler avec success");
    }




    @GetMapping("/{offerId}")
    public ResponseEntity fetchPostuleOffers(@PathVariable Long offerId){
        return ResponseEntity.ok(postuleService.findPostuleOffers(offerId));
    }

    @GetMapping("/{offerId}/{status}")
    public ResponseEntity fetchPostuleOffersByStatus(@PathVariable Long offerId,@PathVariable String status){

        return ResponseEntity.ok(postuleService.FindPostuleByStatus(offerId,status));

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
