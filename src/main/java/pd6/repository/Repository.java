package pd6.repository;

import java.util.*;

public class Repository<K, V> {
    private final Map<K, V> entityBase = new HashMap<>();

    public void addEntity(K key, V value) {
        entityBase.put(key, value);
    }

    public Optional<V> findEntity(K key) {
        return Optional.ofNullable(entityBase.get(key));
    }

    public void removeEntity(K key) {
        entityBase.remove(key);
    }

    public Collection<V> getAllEntities() {
        Collection<V> values = entityBase.values();
        return values;
    }
}
