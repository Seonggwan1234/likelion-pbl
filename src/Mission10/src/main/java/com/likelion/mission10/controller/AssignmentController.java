package com.likelion.mission10.controller;

import com.likelion.mission10.dto.AssignmentCreateRequest;
import com.likelion.mission10.dto.AssignmentResponse;
import com.likelion.mission10.dto.AssignmentUpdateRequest;
import com.likelion.mission10.service.AssignmentService;
import jakarta.validation.Valid;
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
    public ResponseEntity<AssignmentResponse> create(@Valid @RequestBody AssignmentCreateRequest request) {
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

    @GetMapping("/search")
    public ResponseEntity<List<AssignmentResponse>> search(@RequestParam(required = false) String title) {
        List<AssignmentResponse> responses = assignmentService.search(title).stream()
                .map(AssignmentResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new AssignmentResponse(assignmentService.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssignmentResponse> update(@PathVariable Long id,
                                                       @Valid @RequestBody AssignmentUpdateRequest request) {
        return ResponseEntity.ok(new AssignmentResponse(assignmentService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        assignmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
