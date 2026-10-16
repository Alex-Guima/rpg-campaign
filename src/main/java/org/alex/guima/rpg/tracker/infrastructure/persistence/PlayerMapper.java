package org.alex.guima.rpg.tracker.infrastructure.persistence;

import org.alex.guima.rpg.tracker.domain.model.Player;

public class PlayerMapper {
    private PlayerMapper() {}

    public static Player toDomain(PlayerEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Player(entity.id, entity.getName());
    }
}
