package Mission05.service;

import Mission05.domain.Lion;
import Mission05.repository.LionRepository;

import java.util.List;
import java.util.Optional;

public class LionService {

    private final LionRepository repository;

    // 생성자 주입: 어떤 Repository 구현체를 쓸지 외부에서 결정
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

    public void printAll() {
        List<Lion> all = repository.findAll();
        if (all.isEmpty()) {
            System.out.println("등록된 멤버가 없습니다.");
            return;
        }
        for (Lion lion : all) {
            lion.introduce();
        }
    }

    public void printByPart(String part) {
        List<Lion> list = repository.findByPart(part);
        if (list.isEmpty()) {
            System.out.println("[" + part + "] 파트 멤버가 없습니다.");
            return;
        }
        for (Lion lion : list) {
            System.out.println(lion.getInfo());
        }
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
