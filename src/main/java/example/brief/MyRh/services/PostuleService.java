package example.brief.MyRh.services;

import example.brief.MyRh.dtos.PostuleDto;
import example.brief.MyRh.dtos.offre.request.RequestPostuleOffre;

import java.util.List;

public interface PostuleService {
    PostuleDto potuleOffre(RequestPostuleOffre requestPostuleOffre);

    List<PostuleDto> findPostuleOffers(Long offereId);

    List<PostuleDto> FindPostuleByStatus(Long offerId,String Status);
}
