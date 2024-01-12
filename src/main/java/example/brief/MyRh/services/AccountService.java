package example.brief.MyRh.services;

import example.brief.MyRh.dtos.paiement.AccountDTO;
import example.brief.MyRh.dtos.paiement.RequestPaiement;

public interface AccountService {
    boolean effectedPayment(RequestPaiement requestPaiement);
}
