package tn.esprit.artifact.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import static tn.esprit.artifact.entity.EvaluationType.FORUSER;

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
    @JsonBackReference("competance_evaluation_reference")  // Use if there's a reciprocal reference in JobPosition
    Competence competence;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("evaluation_user_reference")
    private User user;

    private Long  note;

    public void calculNote() {
        if (eval != EvaluationType.FORUSER) { // Ensure FORUSER is a valid EvaluationType
            switch (eval) {
                case EXCELLENT:
                    this.note = 5L;
                    break;
                case SATISFAISANT:
                    this.note = 2L;
                    break;
                case INSATISFAISANT:
                    this.note = 1L;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown EvaluationType: " + eval);
            }
        } else {
            this.note = null;
        }
    }


}
