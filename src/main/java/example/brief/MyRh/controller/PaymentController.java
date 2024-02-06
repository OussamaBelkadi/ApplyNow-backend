package example.brief.MyRh.controller;

import com.stripe.param.checkout.SessionCreateParams;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import example.brief.MyRh.entities.CheckoutPayment;
import example.brief.MyRh.services.SocieteService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value = "/api")
public class PaymentController {

    @Value("${stripe.api.key}")
    String secretKey;
    private static Gson gson = new Gson();
    private final SocieteService societeService;

    public PaymentController(SocieteService societeService) {
        this.societeService = societeService;
    }


    private  void init() {
        Stripe.apiKey = this.secretKey;
    }
    @PostMapping("/payment")
    public ResponseEntity<String> paymentWithCheckoutPage(@RequestBody CheckoutPayment payment) throws StripeException {
        init();
        // We create a  stripe session parameters
        SessionCreateParams params = SessionCreateParams.builder()
                // We will use the credit card payment method
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(payment.getSuccessUrl())
                .setCancelUrl(
                        payment.getCancelUrl())
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency(payment.getCurrency()).setUnitAmount(payment.getAmount() * 100).build())
                                .build())

                .build();
        boolean result = this.societeService.verificationSubscription(payment.getSociete_id(), payment.getStatus());
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("Validation realized successfully");
        } else return ResponseEntity.status(HttpStatus.CONFLICT).body("Field payment");

    }

}
