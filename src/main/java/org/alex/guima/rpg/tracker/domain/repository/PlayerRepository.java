package org.alex.guima.rpg.tracker.domain.repository;

import org.alex.guima.rpg.tracker.domain.model.Player;
import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository {
    Optional<Player> findById(UUID id);
}
