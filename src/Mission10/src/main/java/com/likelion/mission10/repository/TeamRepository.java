package com.likelion.mission10.repository;

import com.likelion.mission10.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findByNameContainingIgnoreCase(String name);
}
