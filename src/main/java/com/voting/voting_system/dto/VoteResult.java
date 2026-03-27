package com.voting.voting_system.dto;

import lombok.Data;

@Data
public class VoteResult {
    private Long candidateId;
    private String candidateName;
    private Long voteCount;

    public VoteResult(Long candidateId, String candidateName, Long voteCount) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.voteCount = voteCount;
    }
}