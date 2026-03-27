package com.voting.voting_system.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ElectionRequest {
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}