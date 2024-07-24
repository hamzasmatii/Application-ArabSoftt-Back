package tn.esprit.artifact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.artifact.entity.Evaluation;
import tn.esprit.artifact.service.IEvaluationService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EvaluationController {
    @Autowired
    IEvaluationService evaluationService;

    @PostMapping("/evaluation")
    public ResponseEntity<Evaluation> addEvaluation(@RequestBody Evaluation evaluation) {
        Evaluation addedEvaluation = evaluationService.createEvaluation(evaluation);
        return new ResponseEntity<>(addedEvaluation, HttpStatus.CREATED);
    }

    @GetMapping("/evaluation/{evaluationId}")
    public ResponseEntity<Evaluation> showEvaluationByid(@PathVariable Long evaluationId) {
        Evaluation evaluation = evaluationService.getEvaluationById(evaluationId);
        if (evaluation != null) {
            return ResponseEntity.ok(evaluation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/evaluation")
    public ResponseEntity<List<Evaluation>> getAllEvaluations() {
        List<Evaluation> evaluations = evaluationService.getAllEvaluations();
        return ResponseEntity.ok(evaluations);
    }

    @PutMapping("/evaluation/{evaluationId}")
    public ResponseEntity<Evaluation> updateEvaluation(@PathVariable("evaluationId") Long evaluationId, @RequestBody Evaluation updatedEvaluation) {
        try {
            Evaluation updated = evaluationService.updateEvaluation(evaluationId, updatedEvaluation);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/evaluation/{evaluationId}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long evaluationId) {
        try {
            Evaluation deletedEvaluation = evaluationService.deleteEvaluation(evaluationId);

            // Create the response message
            String message = "Evaluation with ID " + evaluationId + " deleted successfully.";

            // Create the response headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Message", message);
            // Return the response entity with the response object and status OK
            return new ResponseEntity<>(headers, HttpStatus.OK);
        }

        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }



}
