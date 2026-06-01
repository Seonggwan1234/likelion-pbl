package com.likelion.mission07.repository;

import com.likelion.mission07.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemberRepository {

    private final List<Member> store = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(1);

    public Member save(Member member) {
        store.add(member);
        return member;
    }

    public List<Member> findAll() {
        return new ArrayList<>(store);
    }

    public Optional<Member> findById(Long id) {
        return store.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }

    public boolean deleteById(Long id) {
        return store.removeIf(m -> m.getId().equals(id));
    }

    public Long nextId() {
        return sequence.getAndIncrement();
    }
}
