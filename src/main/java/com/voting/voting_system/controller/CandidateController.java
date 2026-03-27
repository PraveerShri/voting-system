package com.voting.voting_system.controller;

import com.voting.voting_system.dto.CandidateRequest;
import com.voting.voting_system.model.Candidate;
import com.voting.voting_system.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping
    public ResponseEntity<?> addCandidate(@RequestBody CandidateRequest request) {
        try {
            Candidate candidate = candidateService.addCandidate(request);
            return ResponseEntity.ok(candidate);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/election/{electionId}")
    public ResponseEntity<List<Candidate>> getCandidatesByElection(
            @PathVariable Long electionId) {
        return ResponseEntity.ok(candidateService.getCandidatesByElection(electionId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCandidate(@PathVariable Long id) {
        try {
            candidateService.deleteCandidate(id);
            return ResponseEntity.ok("Candidate deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}