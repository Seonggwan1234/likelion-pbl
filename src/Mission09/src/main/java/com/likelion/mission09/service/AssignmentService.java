package com.likelion.mission09.service;

import com.likelion.mission09.domain.Assignment;
import com.likelion.mission09.domain.Member;
import com.likelion.mission09.dto.AssignmentCreateRequest;
import com.likelion.mission09.dto.AssignmentUpdateRequest;
import com.likelion.mission09.repository.AssignmentRepository;
import com.likelion.mission09.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다. id=" + request.getMemberId()));
        Assignment assignment = new Assignment(request.getTitle(), request.getContent(), member);
        return assignmentRepository.save(assignment);
    }

    public List<Assignment> findAll() {
        return assignmentRepository.findAll();
    }

    public Optional<Assignment> findById(Long id) {
        return assignmentRepository.findById(id);
    }

    @Transactional
    public Optional<Assignment> update(Long id, AssignmentUpdateRequest request) {
        return assignmentRepository.findById(id).map(assignment -> {
            assignment.update(request.getTitle(), request.getContent());
            return assignment;
        });
    }

    @Transactional
    public boolean delete(Long id) {
        if (!assignmentRepository.existsById(id)) {
            return false;
        }
        assignmentRepository.deleteById(id);
        return true;
    }
}
