package tn.esprit.artifact.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;

    @OneToOne(mappedBy = "poste")
    @JsonBackReference
    private User utilisateurs;

    @OneToMany( cascade = CascadeType.ALL,mappedBy = "jobPosition")
    private Set<Competence> competencesRequises;



}
