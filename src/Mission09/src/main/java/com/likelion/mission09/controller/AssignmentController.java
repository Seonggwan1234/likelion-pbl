package com.likelion.mission09.controller;

import com.likelion.mission09.dto.AssignmentCreateRequest;
import com.likelion.mission09.dto.AssignmentResponse;
import com.likelion.mission09.dto.AssignmentUpdateRequest;
import com.likelion.mission09.service.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping
    public ResponseEntity<AssignmentResponse> create(@RequestBody AssignmentCreateRequest request) {
        AssignmentResponse response = new AssignmentResponse(assignmentService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AssignmentResponse>> findAll() {
        List<AssignmentResponse> responses = assignmentService.findAll().stream()
                .map(AssignmentResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentResponse> findById(@PathVariable Long id) {
        return assignmentService.findById(id)
                .map(assignment -> ResponseEntity.ok(new AssignmentResponse(assignment)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssignmentResponse> update(@PathVariable Long id,
                                                       @RequestBody AssignmentUpdateRequest request) {
        return assignmentService.update(id, request)
                .map(assignment -> ResponseEntity.ok(new AssignmentResponse(assignment)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (assignmentService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
