package example.brief.MyRh.entities;

import example.brief.MyRh.Enum.UserRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="USER_INFO")
public class UserInfoEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(nullable = false, name = "EMAIL", unique = true)
    private String email;

    @Column(nullable = false, name = "PASSWORD")
    private String password;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    private String image;
    private String adress;
    @Column
    @Enumerated(EnumType.STRING)
    private UserRoles roles;


}
