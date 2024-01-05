package example.brief.MyRh.dtos.offre.request;

import lombok.Getter;
import lombok.Setter;
import example.brief.MyRh.dtos.OffreDTO;
@Getter @Setter
public class RequestOffre {
    private Long societeId;
    private OffreDTO offreDTO;
}