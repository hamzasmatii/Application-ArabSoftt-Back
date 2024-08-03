package tn.esprit.artifact.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private EvaluationType eval;

    @ManyToOne
    @JsonBackReference  // Use if there's a reciprocal reference in JobPosition
    Competence competence;

    private int note;

    public void calculNote() {
        switch (eval) {
            case EXCELLENT:
                this.note = 5;
                break;
            case SATISFAISANT:
                this.note = 2;
                break;
            case INSATISFAISANT:
                this.note = 1;
                break;
        }
    }



}
