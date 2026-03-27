package com.voting.voting_system.repository;

import com.voting.voting_system.model.Election;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectionRepository extends JpaRepository<Election, Long> {
}