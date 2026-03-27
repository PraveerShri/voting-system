package com.voting.voting_system.dto;

import lombok.Data;

@Data
public class CandidateRequest {
    private String name;
    private String description;
    private Long electionId;
}