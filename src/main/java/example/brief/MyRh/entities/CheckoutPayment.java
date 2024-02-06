package example.brief.MyRh.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckoutPayment {

    private Long societe_id;
    private String status;
    private String currency;
    private String successUrl;
    private String cancelUrl;
    private long amount;



}
