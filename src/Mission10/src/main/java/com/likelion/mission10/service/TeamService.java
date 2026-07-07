package com.likelion.mission10.service;

import com.likelion.mission10.domain.Member;
import com.likelion.mission10.domain.Team;
import com.likelion.mission10.dto.TeamCreateRequest;
import com.likelion.mission10.dto.TeamUpdateRequest;
import com.likelion.mission10.exception.TeamNotFoundException;
import com.likelion.mission10.repository.MemberRepository;
import com.likelion.mission10.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TeamService {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    public TeamService(TeamRepository teamRepository, MemberRepository memberRepository) {
        this.teamRepository = teamRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Team create(TeamCreateRequest request) {
        return teamRepository.save(new Team(request.getName()));
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public Team findById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(id));
    }

    public List<Team> search(String name) {
        if (name == null || name.isBlank()) {
            return teamRepository.findAll();
        }
        return teamRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Member> findMembers(Long teamId) {
        findById(teamId);
        return memberRepository.findByTeamId(teamId);
    }

    @Transactional
    public Team update(Long id, TeamUpdateRequest request) {
        Team team = findById(id);
        team.update(request.getName());
        return team;
    }

    @Transactional
    public void delete(Long id) {
        Team team = findById(id);
        teamRepository.delete(team);
    }
}
