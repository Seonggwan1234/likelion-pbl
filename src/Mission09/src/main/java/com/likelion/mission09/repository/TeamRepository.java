package com.likelion.mission09.repository;

import com.likelion.mission09.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
