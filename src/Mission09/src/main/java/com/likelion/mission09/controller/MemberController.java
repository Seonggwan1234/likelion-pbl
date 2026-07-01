package com.likelion.mission09.controller;

import com.likelion.mission09.dto.AssignmentResponse;
import com.likelion.mission09.dto.MemberCreateRequest;
import com.likelion.mission09.dto.MemberResponse;
import com.likelion.mission09.dto.MemberUpdateRequest;
import com.likelion.mission09.service.MemberService;
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
    public ResponseEntity<MemberResponse> create(@RequestBody MemberCreateRequest request) {
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

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> findById(@PathVariable Long id) {
        return memberService.findById(id)
                .map(member -> ResponseEntity.ok(new MemberResponse(member)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/assignments")
    public ResponseEntity<List<AssignmentResponse>> findAssignments(@PathVariable Long id) {
        if (memberService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<AssignmentResponse> responses = memberService.findAssignments(id).stream()
                .map(AssignmentResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> update(@PathVariable Long id,
                                                  @RequestBody MemberUpdateRequest request) {
        return memberService.update(id, request)
                .map(member -> ResponseEntity.ok(new MemberResponse(member)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (memberService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
