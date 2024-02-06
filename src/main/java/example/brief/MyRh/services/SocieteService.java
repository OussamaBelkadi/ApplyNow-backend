package example.brief.MyRh.services;

import example.brief.MyRh.dtos.SocieteDTO;
import example.brief.MyRh.dtos.societe.RequestCreateSocieteDTO;
import example.brief.MyRh.entities.Postule;

import java.io.IOException;

public interface SocieteService {
    SocieteDTO createSociete(RequestCreateSocieteDTO societeDTO) throws IOException;
    SocieteDTO loginSociete(SocieteDTO societeDTO);
    Boolean verificationCode(String Code);
    Boolean verificationSubscription(Long companyId, String subscriptionStatus);


}
