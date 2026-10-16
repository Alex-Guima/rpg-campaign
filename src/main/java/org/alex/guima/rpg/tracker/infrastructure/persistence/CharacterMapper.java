package org.alex.guima.rpg.tracker.infrastructure.persistence;

import org.alex.guima.rpg.tracker.domain.model.Character;
import org.alex.guima.rpg.tracker.domain.model.PlayableCharacter;
import org.alex.guima.rpg.tracker.domain.model.NonPlayableCharacter;

public class CharacterMapper {
    private CharacterMapper() {}

    public static Character toDomain(CharacterEntity entity) {
        if (entity == null) {
            return null;
        }

        if (entity.getPlayer() != null || entity.getCharacterClass() != null) {
            return new PlayableCharacter(
                entity.id,
                entity.getName(),
                entity.getDescription(),
                CampaignMapper.toDomain(entity.getCampaign()),
                CharacterClassMapper.toDomain(entity.getCharacterClass()),
                PlayerMapper.toDomain(entity.getPlayer())
            );
        } else {
            return new NonPlayableCharacter(
                entity.id,
                entity.getName(),
                entity.getDescription(),
                CampaignMapper.toDomain(entity.getCampaign())
            );
        }
    }
}
