package Mission05.repository;

import Mission05.domain.Lion;

import java.util.*;

public class MockLionRepository implements LionRepository {

    private final List<Lion> store;

    public MockLionRepository(List<Lion> initialData) {
        this.store = new ArrayList<>(initialData);
    }

    @Override
    public void save(Lion lion) {
        store.add(lion);
    }

    @Override
    public Optional<Lion> findByName(String name) {
        for (Lion lion : store) {
            if (lion.getName().equals(name)) {
                return Optional.of(lion);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Lion> findAll() {
        return Collections.unmodifiableList(store);
    }

    @Override
    public List<Lion> findByPart(String part) {
        List<Lion> result = new ArrayList<>();
        for (Lion lion : store) {
            if (lion.getPart().equals(part)) {
                result.add(lion);
            }
        }
        return result;
    }

    @Override
    public boolean delete(String name) {
        return store.removeIf(lion -> lion.getName().equals(name));
    }
}
