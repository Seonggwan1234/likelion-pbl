package com.likelion.mission06.service;

import com.likelion.mission06.domain.Lion;
import com.likelion.mission06.repository.LionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LionService {

    private final LionRepository repository;

    // 생성자 주입: Spring이 LionRepository 구현체를 자동으로 주입
    public LionService(LionRepository repository) {
        this.repository = repository;
    }

    public boolean register(Lion lion) {
        Optional<Lion> existing = repository.findByName(lion.getName());
        if (existing.isPresent()) {
            System.out.println("[중복] " + lion.getName() + " 은(는) 이미 등록된 멤버입니다.");
            return false;
        }
        repository.save(lion);
        System.out.println("[등록] " + lion.getName() + " 등록 완료.");
        return true;
    }

    public List<Lion> findAll() {
        return repository.findAll();
    }

    public List<Lion> findByPart(String part) {
        return repository.findByPart(part);
    }

    public boolean remove(String name) {
        boolean deleted = repository.delete(name);
        if (deleted) {
            System.out.println("[삭제] " + name + " 삭제 완료.");
        } else {
            System.out.println("[실패] " + name + " 을(를) 찾을 수 없습니다.");
        }
        return deleted;
    }
}
