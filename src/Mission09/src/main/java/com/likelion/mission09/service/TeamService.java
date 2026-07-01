package com.likelion.mission09.service;

import com.likelion.mission09.domain.Member;
import com.likelion.mission09.domain.Team;
import com.likelion.mission09.dto.TeamCreateRequest;
import com.likelion.mission09.dto.TeamUpdateRequest;
import com.likelion.mission09.repository.MemberRepository;
import com.likelion.mission09.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Optional<Team> findById(Long id) {
        return teamRepository.findById(id);
    }

    public List<Member> findMembers(Long teamId) {
        return memberRepository.findByTeamId(teamId);
    }

    @Transactional
    public Optional<Team> update(Long id, TeamUpdateRequest request) {
        return teamRepository.findById(id).map(team -> {
            team.update(request.getName());
            return team;
        });
    }

    @Transactional
    public boolean delete(Long id) {
        if (!teamRepository.existsById(id)) {
            return false;
        }
        teamRepository.deleteById(id);
        return true;
    }
}
