package example.brief.MyRh.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostuleDto {
    private String nom_complet;
    private String cv;
    private int tel;
    private String motivation;
}
