package example.brief.MyRh.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "speciality", uniqueConstraints = {
                                @UniqueConstraint(columnNames = "name")
                            }
)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Speciality {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
}
