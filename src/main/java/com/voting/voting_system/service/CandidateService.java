package com.voting.voting_system.service;

import com.voting.voting_system.dto.CandidateRequest;
import com.voting.voting_system.model.Candidate;
import com.voting.voting_system.model.Election;
import com.voting.voting_system.repository.CandidateRepository;
import com.voting.voting_system.repository.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ElectionRepository electionRepository;

    public Candidate addCandidate(CandidateRequest request) {
        Election election = electionRepository.findById(request.getElectionId())
                .orElseThrow(() -> new RuntimeException("Election not found"));

        Candidate candidate = new Candidate();
        candidate.setName(request.getName());
        candidate.setDescription(request.getDescription());
        candidate.setElection(election);

        return candidateRepository.save(candidate);
    }

    public List<Candidate> getCandidatesByElection(Long electionId) {
        return candidateRepository.findByElectionId(electionId);
    }

    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }
}