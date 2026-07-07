package com.likelion.mission10.service;

import com.likelion.mission10.domain.Assignment;
import com.likelion.mission10.domain.Member;
import com.likelion.mission10.dto.AssignmentCreateRequest;
import com.likelion.mission10.dto.AssignmentUpdateRequest;
import com.likelion.mission10.exception.AssignmentNotFoundException;
import com.likelion.mission10.exception.MemberNotFoundException;
import com.likelion.mission10.repository.AssignmentRepository;
import com.likelion.mission10.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final MemberRepository memberRepository;

    public AssignmentService(AssignmentRepository assignmentRepository, MemberRepository memberRepository) {
        this.assignmentRepository = assignmentRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Assignment create(AssignmentCreateRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberNotFoundException(request.getMemberId()));
        Assignment assignment = new Assignment(request.getTitle(), request.getContent(), member);
        return assignmentRepository.save(assignment);
    }

    public List<Assignment> findAll() {
        return assignmentRepository.findAll();
    }

    public Assignment findById(Long id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new AssignmentNotFoundException(id));
    }

    public List<Assignment> search(String title) {
        if (title == null || title.isBlank()) {
            return assignmentRepository.findAll();
        }
        return assignmentRepository.findByTitleContainingIgnoreCase(title);
    }

    @Transactional
    public Assignment update(Long id, AssignmentUpdateRequest request) {
        Assignment assignment = findById(id);
        assignment.update(request.getTitle(), request.getContent());
        return assignment;
    }

    @Transactional
    public void delete(Long id) {
        Assignment assignment = findById(id);
        assignmentRepository.delete(assignment);
    }
}
