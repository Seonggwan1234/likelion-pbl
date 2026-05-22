package Mission05.repository;

import Mission05.domain.Lion;

import java.util.*;

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
