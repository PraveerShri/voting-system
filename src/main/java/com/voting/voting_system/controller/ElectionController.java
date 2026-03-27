package com.voting.voting_system.controller;

import com.voting.voting_system.dto.ElectionRequest;
import com.voting.voting_system.model.Election;
import com.voting.voting_system.service.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/elections")
public class ElectionController {

    @Autowired
    private ElectionService electionService;

    @PostMapping
    public ResponseEntity<?> createElection(@RequestBody ElectionRequest request) {
        try {
            Election election = electionService.createElection(request);
            return ResponseEntity.ok(election);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Election>> getAllElections() {
        return ResponseEntity.ok(electionService.getAllElections());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getElectionById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(electionService.getElectionById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id,
                                          @RequestParam Election.Status status) {
        try {
            return ResponseEntity.ok(electionService.updateElectionStatus(id, status));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteElection(@PathVariable Long id) {
        try {
            electionService.deleteElection(id);
            return ResponseEntity.ok("Election deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/results")
    public ResponseEntity<?> getElectionResults(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(electionService.getElectionResults(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}