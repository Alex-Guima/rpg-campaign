package org.alex.guima.rpg.tracker.domain.repository;

import org.alex.guima.rpg.tracker.domain.model.Character;
import java.util.Optional;
import java.util.UUID;

public interface CharacterRepository {
    Character save(Character character);
    Optional<Character> findById(UUID id);
}
