package example.brief.MyRh.dtos;

import example.brief.MyRh.Enum.NiveauEtude;
import example.brief.MyRh.Enum.StatusOffre;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OffreDTO {

    private Long id;
    private String titre;
    private String description;
    private String profile;
    private float salaire;
    private StatusOffre status;
    private NiveauEtude niveau_etude;

    private Long idSociete;

}
