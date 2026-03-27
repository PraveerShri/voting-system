package com.voting.voting_system.service;

import com.voting.voting_system.dto.ElectionRequest;
import com.voting.voting_system.model.Election;
import com.voting.voting_system.repository.ElectionRepository;
import com.voting.voting_system.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ElectionService {

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private VoteRepository voteRepository;

    public Election createElection(ElectionRequest request) {
        Election election = new Election();
        election.setTitle(request.getTitle());
        election.setDescription(request.getDescription());
        election.setStartDate(request.getStartDate());
        election.setEndDate(request.getEndDate());
        election.setStatus(Election.Status.UPCOMING);
        return electionRepository.save(election);
    }

    public List<Election> getAllElections() {
        return electionRepository.findAll();
    }

    public Election getElectionById(Long id) {
        return electionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Election not found"));
    }

    public Election updateElectionStatus(Long id, Election.Status status) {
        Election election = getElectionById(id);
        election.setStatus(status);
        return electionRepository.save(election);
    }

    public void deleteElection(Long id) {
        electionRepository.deleteById(id);
    }

    public List<Map<String, Object>> getElectionResults(Long electionId) {
        List<Object[]> results = voteRepository.findVoteResultsByElectionId(electionId);
        return results.stream()
                .map(row -> Map.of(
                        "candidateId", row[0],
                        "candidateName", row[1],
                        "votes", row[2]
                ))
                .collect(Collectors.toList());
    }
}