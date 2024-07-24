package tn.esprit.artifact.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String identifiantUser;
    private String nom;
    private String prenom;
    private String mdp;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @OneToOne
    @JoinColumn(name = "job_position_id")
    @JsonManagedReference
    private JobPosition poste;

    @ManyToOne
    @JoinColumn(name = "serviceEq_id")
    @JsonBackReference
    private ServiceEq serviceEq;

    @PrePersist
    private void generateIdentifiantUser() {
        String baseIdentifiant = generateUniqueNumber();
        this.identifiantUser = nom  + "-" + generateUniqueNumber();
    }

    // Méthode pour générer un nombre unique
    private String generateUniqueNumber() {
        // Utilisez un UUID pour générer un nombre unique
        return UUID.randomUUID().toString().substring(0, 5); // Limité à 8 caractères
    }


    // Getters and setters


}
