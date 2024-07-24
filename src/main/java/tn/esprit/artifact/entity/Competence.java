package tn.esprit.artifact.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;


    @ManyToOne
    @JsonIgnore
    JobPosition jobPosition;

    @OneToMany( cascade = CascadeType.ALL,mappedBy = "competence")
    private Set<Evaluation> evaluations;




}
