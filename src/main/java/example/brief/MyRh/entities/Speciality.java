package example.brief.MyRh.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "speciality", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
}
)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Speciality {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "speciality")
    private List<Question> questions;
}