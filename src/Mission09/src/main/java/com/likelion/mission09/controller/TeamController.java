package com.likelion.mission09.controller;

import com.likelion.mission09.dto.MemberResponse;
import com.likelion.mission09.dto.TeamCreateRequest;
import com.likelion.mission09.dto.TeamResponse;
import com.likelion.mission09.dto.TeamUpdateRequest;
import com.likelion.mission09.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<TeamResponse> create(@RequestBody TeamCreateRequest request) {
        TeamResponse response = new TeamResponse(teamService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TeamResponse>> findAll() {
        List<TeamResponse> responses = teamService.findAll().stream()
                .map(TeamResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponse> findById(@PathVariable Long id) {
        return teamService.findById(id)
                .map(team -> ResponseEntity.ok(new TeamResponse(team)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/members")
    public ResponseEntity<List<MemberResponse>> findMembers(@PathVariable Long id) {
        if (teamService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<MemberResponse> responses = teamService.findMembers(id).stream()
                .map(MemberResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamResponse> update(@PathVariable Long id,
                                                @RequestBody TeamUpdateRequest request) {
        return teamService.update(id, request)
                .map(team -> ResponseEntity.ok(new TeamResponse(team)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (teamService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
