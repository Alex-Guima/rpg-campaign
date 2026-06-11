package org.alex.guima.rpg.tracker.usecase;

import org.alex.guima.rpg.tracker.domain.model.Character;
import org.alex.guima.rpg.tracker.domain.repository.CharacterRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class FakeCharacterRepository implements CharacterRepository {
    private final Map<UUID, Character> store = new HashMap<>();

    @Override
    public Character save(Character character) {
        if (character.id() == null) {
            character.setId(UUID.randomUUID());
        }
        store.put(character.id(), character);
        return character;
    }

    @Override
    public Optional<Character> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }
}
