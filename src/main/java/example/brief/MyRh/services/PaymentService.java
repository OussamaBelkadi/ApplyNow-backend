package example.brief.MyRh.services;

import com.stripe.exception.StripeException;
import example.brief.MyRh.dtos.StripeChargeDto;
import example.brief.MyRh.dtos.StripeTokenDto;

public interface PaymentService {

    boolean isCompanyPaymentValid(String companyId);

    //based on the subscription status, the company will be able to pay for the subscription
    boolean pay(String token , double amount)throws StripeException;
    boolean cancel(String companyId);
    StripeTokenDto createCardToken(StripeTokenDto model);
    StripeChargeDto charge(StripeChargeDto stripeChargeDto);

}
