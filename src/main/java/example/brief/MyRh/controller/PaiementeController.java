package example.brief.MyRh.controller;

import example.brief.MyRh.dtos.paiement.RequestPaiement;
import example.brief.MyRh.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/paiement")
public class PaiementeController {
    private AccountService accountService;

    public PaiementeController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("")
    public ResponseEntity<String> paiement(@RequestBody RequestPaiement requestPaiement){
        boolean result = this.accountService.effectedPayment(requestPaiement);
        if (result){
            return ResponseEntity.ok("The payment  is realized");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("the payment is field");
        }
    }
}
