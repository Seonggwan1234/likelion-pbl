package com.likelion.mission09.repository;

import com.likelion.mission09.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByTeamId(Long teamId);
}
