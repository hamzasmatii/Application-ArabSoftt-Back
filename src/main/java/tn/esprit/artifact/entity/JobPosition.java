package tn.esprit.artifact.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class JobPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;

    @OneToOne(mappedBy = "poste")
    @JsonBackReference("poste_user_reference")
    private User utilisateurs;

    @OneToMany( cascade = CascadeType.ALL,mappedBy = "jobPosition")
    @JsonManagedReference  // Indicates that this is the parent side of the relationship
    private Set<Competence> competencesRequises;



}
