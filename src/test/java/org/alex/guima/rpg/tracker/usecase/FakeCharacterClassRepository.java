package org.alex.guima.rpg.tracker.usecase;

import org.alex.guima.rpg.tracker.domain.model.CharacterClass;
import org.alex.guima.rpg.tracker.domain.repository.CharacterClassRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class FakeCharacterClassRepository implements CharacterClassRepository {
    private final Map<UUID, CharacterClass> store = new HashMap<>();

    public void addCharacterClass(CharacterClass characterClass) {
        store.put(characterClass.id(), characterClass);
    }

    @Override
    public Optional<CharacterClass> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public CharacterClass save(CharacterClass item) {
        if (item.id() == null) {
            item.setId(UUID.randomUUID());
        }
        store.put(item.id(), item);
        return item;
    }

    @Override
    public List<CharacterClass> findAll() {
        return new ArrayList<>(store.values());
    }
}
