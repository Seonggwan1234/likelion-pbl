package com.likelion.mission10.controller;

import com.likelion.mission10.dto.MemberResponse;
import com.likelion.mission10.dto.TeamCreateRequest;
import com.likelion.mission10.dto.TeamResponse;
import com.likelion.mission10.dto.TeamUpdateRequest;
import com.likelion.mission10.service.TeamService;
import jakarta.validation.Valid;
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
    public ResponseEntity<TeamResponse> create(@Valid @RequestBody TeamCreateRequest request) {
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

    @GetMapping("/search")
    public ResponseEntity<List<TeamResponse>> search(@RequestParam(required = false) String name) {
        List<TeamResponse> responses = teamService.search(name).stream()
                .map(TeamResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new TeamResponse(teamService.findById(id)));
    }

    @GetMapping("/{id}/members")
    public ResponseEntity<List<MemberResponse>> findMembers(@PathVariable Long id) {
        List<MemberResponse> responses = teamService.findMembers(id).stream()
                .map(MemberResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamResponse> update(@PathVariable Long id,
                                                @Valid @RequestBody TeamUpdateRequest request) {
        return ResponseEntity.ok(new TeamResponse(teamService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teamService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
