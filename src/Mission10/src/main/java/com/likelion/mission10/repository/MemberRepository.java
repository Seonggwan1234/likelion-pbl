package com.likelion.mission10.repository;

import com.likelion.mission10.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByTeamId(Long teamId);

    List<Member> findByNameContainingIgnoreCaseOrPartContainingIgnoreCase(String name, String part);
}
