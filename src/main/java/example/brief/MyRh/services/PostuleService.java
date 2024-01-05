package example.brief.MyRh.services;

import example.brief.MyRh.dtos.PostuleDto;
import example.brief.MyRh.dtos.offre.request.RequestPostuleOffre;

public interface PostuleService {
    PostuleDto potuleOffre(RequestPostuleOffre requestPostuleOffre);
}
