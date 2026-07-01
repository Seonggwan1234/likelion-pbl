package com.likelion.mission09.service;

import com.likelion.mission09.domain.Assignment;
import com.likelion.mission09.domain.Member;
import com.likelion.mission09.domain.Team;
import com.likelion.mission09.dto.MemberCreateRequest;
import com.likelion.mission09.dto.MemberUpdateRequest;
import com.likelion.mission09.repository.AssignmentRepository;
import com.likelion.mission09.repository.MemberRepository;
import com.likelion.mission09.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final AssignmentRepository assignmentRepository;

    public MemberService(MemberRepository memberRepository, TeamRepository teamRepository,
                          AssignmentRepository assignmentRepository) {
        this.memberRepository = memberRepository;
        this.teamRepository = teamRepository;
        this.assignmentRepository = assignmentRepository;
    }

    @Transactional
    public Member create(MemberCreateRequest request) {
        Team team = findTeamOrNull(request.getTeamId());
        Member member = new Member(request.getName(), request.getAge(), request.getPart(), team);
        return memberRepository.save(member);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    public List<Assignment> findAssignments(Long memberId) {
        return assignmentRepository.findByMemberId(memberId);
    }

    @Transactional
    public Optional<Member> update(Long id, MemberUpdateRequest request) {
        return memberRepository.findById(id).map(member -> {
            member.update(request.getName(), request.getAge(), request.getPart());
            if (request.getTeamId() != null) {
                member.assignTeam(findTeamOrNull(request.getTeamId()));
            }
            return member;
        });
    }

    @Transactional
    public boolean delete(Long id) {
        if (!memberRepository.existsById(id)) {
            return false;
        }
        memberRepository.deleteById(id);
        return true;
    }

    private Team findTeamOrNull(Long teamId) {
        if (teamId == null) {
            return null;
        }
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀입니다. id=" + teamId));
    }
}
