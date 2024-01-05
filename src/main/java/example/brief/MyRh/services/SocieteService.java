package example.brief.MyRh.services;

import example.brief.MyRh.dtos.SocieteDTO;
import example.brief.MyRh.dtos.societe.RequestCreateSocieteDTO;

import java.io.IOException;

public interface SocieteService {
    SocieteDTO createSociete(RequestCreateSocieteDTO societeDTO) throws IOException;
    SocieteDTO loginSociete(SocieteDTO societeDTO);
    Boolean verificationCode(String Code);
}
