package example.brief.MyRh.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String resoponse;
    private boolean status;
    @ManyToOne
    private Question question;

}
