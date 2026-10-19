package pd6.repository;

import pd6.model.EntityIdentifiable;

import java.util.*;

public class Repository<ID, E extends EntityIdentifiable<ID>> {
    private final Map<ID, E> storage = new HashMap<>();

    public void save(E entity) {
        storage.put(entity.getId(), entity);
    }

    public Optional<E> findById(ID id) {
        return Optional.ofNullable(storage.get(id));
    }

    public void deleteById(ID id) {
        if (storage.isEmpty()) {
            throw new IllegalStateException("Id does not exist.");
        } else {
            storage.remove(id);
        }
    }

    public Collection<E> getAll() {
        return storage.values();
    }
}
