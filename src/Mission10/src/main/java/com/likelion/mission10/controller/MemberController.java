package com.likelion.mission10.controller;

import com.likelion.mission10.dto.AssignmentResponse;
import com.likelion.mission10.dto.MemberCreateRequest;
import com.likelion.mission10.dto.MemberResponse;
import com.likelion.mission10.dto.MemberUpdateRequest;
import com.likelion.mission10.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberResponse> create(@Valid @RequestBody MemberCreateRequest request) {
        MemberResponse response = new MemberResponse(memberService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> findAll() {
        List<MemberResponse> responses = memberService.findAll().stream()
                .map(MemberResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MemberResponse>> search(@RequestParam(required = false) String keyword) {
        List<MemberResponse> responses = memberService.search(keyword).stream()
                .map(MemberResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new MemberResponse(memberService.findById(id)));
    }

    @GetMapping("/{id}/assignments")
    public ResponseEntity<List<AssignmentResponse>> findAssignments(@PathVariable Long id) {
        List<AssignmentResponse> responses = memberService.findAssignments(id).stream()
                .map(AssignmentResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> update(@PathVariable Long id,
                                                  @Valid @RequestBody MemberUpdateRequest request) {
        return ResponseEntity.ok(new MemberResponse(memberService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
