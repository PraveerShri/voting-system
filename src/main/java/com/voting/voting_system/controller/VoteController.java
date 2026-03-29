package com.voting.voting_system.controller;

import com.voting.voting_system.dto.VoteRequest;
import com.voting.voting_system.dto.VoteResult;
import com.voting.voting_system.model.Vote;
import com.voting.voting_system.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
@CrossOrigin(origins = "http://localhost:3000")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping
    public ResponseEntity<?> castVote(@RequestBody VoteRequest request) {
        try {
            Vote vote = voteService.castVote(request);
            return ResponseEntity.ok("Vote cast successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/results/{electionId}")
    public ResponseEntity<?> getResults(@PathVariable Long electionId) {
        try {
            List<VoteResult> results = voteService.getResults(electionId);
            return ResponseEntity.ok(results);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}