package example.brief.MyRh.services;

import example.brief.MyRh.dtos.OffreDTO;
import example.brief.MyRh.dtos.offre.request.RequestOffre;
import example.brief.MyRh.dtos.offre.request.RequestSearchOffreDTO;
import example.brief.MyRh.dtos.offre.request.RequestValidationDTO;
import example.brief.MyRh.entities.Offre;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface OffreService {
    OffreDTO createOffre(OffreDTO offreDTO);
    OffreDTO storeOffre(RequestOffre requestOffre);
    OffreDTO validationOffre(RequestValidationDTO validationDTO);
    Page<Offre> listOffrePageable(Map<String, Integer> query);

    List<OffreDTO> SearchOffre(RequestSearchOffreDTO requestSearchOffreDTO);
}
