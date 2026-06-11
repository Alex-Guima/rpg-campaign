package org.alex.guima.rpg.tracker.usecase;

import org.alex.guima.rpg.tracker.domain.model.CharacterClass;
import org.alex.guima.rpg.tracker.domain.repository.CharacterClassRepository;

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
}
