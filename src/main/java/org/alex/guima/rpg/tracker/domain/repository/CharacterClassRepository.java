package org.alex.guima.rpg.tracker.domain.repository;

import org.alex.guima.rpg.tracker.domain.model.CharacterClass;
import java.util.Optional;
import java.util.UUID;

public interface CharacterClassRepository {
    Optional<CharacterClass> findById(UUID id);
}
