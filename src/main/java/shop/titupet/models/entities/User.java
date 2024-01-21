package shop.titupet.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private boolean gender;

    private String img;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Address> addresses;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
