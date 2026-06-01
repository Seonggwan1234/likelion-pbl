package com.likelion.mission07.service;

import com.likelion.mission07.domain.Member;
import com.likelion.mission07.dto.MemberCreateRequest;
import com.likelion.mission07.dto.MemberUpdateRequest;
import com.likelion.mission07.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member create(MemberCreateRequest request) {
        Member member = new Member(
                memberRepository.nextId(),
                request.getName(),
                request.getAge(),
                request.getPart()
        );
        return memberRepository.save(member);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Optional<Member> update(Long id, MemberUpdateRequest request) {
        return memberRepository.findById(id).map(member -> {
            member.update(request.getName(), request.getAge(), request.getPart());
            return member;
        });
    }

    public boolean delete(Long id) {
        return memberRepository.deleteById(id);
    }
}
