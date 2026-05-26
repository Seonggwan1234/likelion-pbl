package com.likelion.mission06.repository;

import com.likelion.mission06.domain.Lion;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryLionRepository implements LionRepository {

    private final Map<String, Lion> store = new LinkedHashMap<>();

    @Override
    public void save(Lion lion) {
        store.put(lion.getName(), lion);
    }

    @Override
    public Optional<Lion> findByName(String name) {
        return Optional.ofNullable(store.get(name));
    }

    @Override
    public List<Lion> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<Lion> findByPart(String part) {
        List<Lion> result = new ArrayList<>();
        for (Lion lion : store.values()) {
            if (lion.getPart().equals(part)) {
                result.add(lion);
            }
        }
        return result;
    }

    @Override
    public boolean delete(String name) {
        return store.remove(name) != null;
    }
}
