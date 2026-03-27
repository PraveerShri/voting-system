package com.voting.voting_system.service;

import com.voting.voting_system.dto.VoteRequest;
import com.voting.voting_system.dto.VoteResult;
import com.voting.voting_system.model.Candidate;
import com.voting.voting_system.model.Election;
import com.voting.voting_system.model.User;
import com.voting.voting_system.model.Vote;
import com.voting.voting_system.repository.CandidateRepository;
import com.voting.voting_system.repository.ElectionRepository;
import com.voting.voting_system.repository.UserRepository;
import com.voting.voting_system.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    public Vote castVote(VoteRequest request) {
        // Check if user already voted in this election
        if (voteRepository.existsByUserIdAndElectionId(
                request.getUserId(), request.getElectionId())) {
            throw new RuntimeException("User has already voted in this election");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Election election = electionRepository.findById(request.getElectionId())
                .orElseThrow(() -> new RuntimeException("Election not found"));

        // Check if election is active
        if (election.getStatus() != Election.Status.ACTIVE) {
            throw new RuntimeException("Election is not active");
        }

        Candidate candidate = candidateRepository.findById(request.getCandidateId())
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        Vote vote = new Vote();
        vote.setUser(user);
        vote.setElection(election);
        vote.setCandidate(candidate);

        return voteRepository.save(vote);
    }

    public List<VoteResult> getResults(Long electionId) {
        List<Object[]> results = voteRepository.findVoteResultsByElectionId(electionId);
        return results.stream()
                .map(row -> new VoteResult(
                        (Long) row[0],
                        (String) row[1],
                        (Long) row[2]))
                .collect(Collectors.toList());
    }
}