package tn.esprit.artifact.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServiceEq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @OneToOne
    @JoinColumn(name = "chef_equipe_id")
    @JsonManagedReference
    private User chefEquipe;

    @OneToMany
    @JoinColumn(name = "serviceEq_id")
    @JsonManagedReference
    private Set<User> employes;
}
