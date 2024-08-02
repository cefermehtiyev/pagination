package az.ingress.dao.entity;

import az.ingress.model.enums.UserStatus;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy =IDENTITY)
    private Long id ;

    private String userName;

    private Integer age;

    private String birthPlace;

    @Enumerated(EnumType.STRING)
    private UserStatus status;


}
