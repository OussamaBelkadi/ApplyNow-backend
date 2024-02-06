package example.brief.MyRh.controller;

import example.brief.MyRh.dtos.OffreDTO;
import example.brief.MyRh.dtos.PostuleDto;
import example.brief.MyRh.dtos.offre.request.RequestOffre;
import example.brief.MyRh.dtos.offre.request.RequestPostuleOffre;
import example.brief.MyRh.dtos.offre.request.RequestValidationDTO;
import example.brief.MyRh.entities.Offre;
import example.brief.MyRh.mappers.OffreMapper;
import example.brief.MyRh.services.PostuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import example.brief.MyRh.services.OffreService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("offre")

public class OffreController {
    private OffreService offreService;
    private final PostuleService postuleService;

    @Autowired
    public OffreController(OffreService offreService, PostuleService postuleService) {

        this.offreService = offreService;
        this.postuleService = postuleService;

    }


    @PostMapping("/create")
    public ResponseEntity<String> createOffre(@RequestBody RequestOffre requestOffre) {
        OffreDTO offreDTO = offreService.storeOffre(requestOffre) ;
        return new ResponseEntity<>("Post created successfully", HttpStatus.CREATED);
    }

    /*@PostMapping("/valide")
    public ResponseEntity<String> validationOffre(@RequestBody OffreDTO offreDTO) {

        return new ResponseEntity<>("Validation realised successfully", HttpStatus.CREATED);
    }
    */
    @PostMapping("/valide")
    public ResponseEntity validationOffre(@RequestBody RequestValidationDTO validationDTO) {
        this.offreService.validationOffre(validationDTO);
        return  ResponseEntity.ok("Validation realised successfully");
    }


//    @PostMapping("/postuler")
//    public ResponseEntity<String> postuleOffre(@RequestBody RequestPostuleOffre requestPostuleOffre){
//        PostuleDto postuleDto = this.postuleService.potuleOffre(requestPostuleOffre);
//        return new ResponseEntity<>("the postule was add successfully " , HttpStatus.CREATED);
//    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity fetchPageable(@PathVariable int page,@PathVariable int size){
        HashMap<String,Integer> query = new HashMap<>();
        query.put("page",page);
        query.put("size",size);
        List<OffreDTO> offreDTOList = new ArrayList<>();
        Page<Offre> offres = this.offreService.listOffrePageable(query);
        for(Offre O : offres){
            OffreDTO offreDTO = OffreMapper.INSTANCE.toDTO(O);
            offreDTO.setStatus(O.getStatus());
            offreDTOList.add(offreDTO);
        }
        return ResponseEntity.ok(offreDTOList);
    }

    @GetMapping("/statistics/{offerId}")
    public ResponseEntity OfferStatistics(@PathVariable Long offerId){

        return ResponseEntity.ok(postuleService.OfferStatistics(offerId));


    }

}
