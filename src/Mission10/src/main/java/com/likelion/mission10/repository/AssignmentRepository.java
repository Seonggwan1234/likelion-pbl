package com.likelion.mission10.repository;

import com.likelion.mission10.domain.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> findByMemberId(Long memberId);

    List<Assignment> findByTitleContainingIgnoreCase(String title);
}
