package com.voting.voting_system.repository;

import com.voting.voting_system.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    boolean existsByUserIdAndElectionId(Long userId, Long electionId);

    @Query("SELECT v.candidate.id, v.candidate.name, COUNT(v) " +
           "FROM Vote v WHERE v.election.id = :electionId " +
           "GROUP BY v.candidate.id, v.candidate.name " +
           "ORDER BY COUNT(v) DESC")
    List<Object[]> findVoteResultsByElectionId(@Param("electionId") Long electionId);
}