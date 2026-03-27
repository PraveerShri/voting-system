package com.voting.voting_system.dto;

import lombok.Data;

@Data
public class VoteRequest {
    private Long userId;
    private Long electionId;
    private Long candidateId;
}