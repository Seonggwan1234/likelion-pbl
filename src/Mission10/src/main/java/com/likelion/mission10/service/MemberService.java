package com.likelion.mission10.service;

import com.likelion.mission10.domain.Assignment;
import com.likelion.mission10.domain.Member;
import com.likelion.mission10.domain.Team;
import com.likelion.mission10.dto.MemberCreateRequest;
import com.likelion.mission10.dto.MemberUpdateRequest;
import com.likelion.mission10.exception.MemberNotFoundException;
import com.likelion.mission10.exception.TeamNotFoundException;
import com.likelion.mission10.repository.AssignmentRepository;
import com.likelion.mission10.repository.MemberRepository;
import com.likelion.mission10.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
    }

    public List<Member> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return memberRepository.findAll();
        }
        return memberRepository.findByNameContainingIgnoreCaseOrPartContainingIgnoreCase(keyword, keyword);
    }

    public List<Assignment> findAssignments(Long memberId) {
        findById(memberId);
        return assignmentRepository.findByMemberId(memberId);
    }

    @Transactional
    public Member update(Long id, MemberUpdateRequest request) {
        Member member = findById(id);
        member.update(request.getName(), request.getAge(), request.getPart());
        if (request.getTeamId() != null) {
            member.assignTeam(findTeamOrNull(request.getTeamId()));
        }
        return member;
    }

    @Transactional
    public void delete(Long id) {
        Member member = findById(id);
        memberRepository.delete(member);
    }

    private Team findTeamOrNull(Long teamId) {
        if (teamId == null) {
            return null;
        }
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException(teamId));
    }
}
