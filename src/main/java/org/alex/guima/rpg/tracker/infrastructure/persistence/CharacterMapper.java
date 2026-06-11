package org.alex.guima.rpg.tracker.infrastructure.persistence;

import org.alex.guima.rpg.tracker.domain.model.Character;
import org.alex.guima.rpg.tracker.domain.model.PlayableCharacter;
import org.alex.guima.rpg.tracker.domain.model.NonPlayableCharacter;

public class CharacterMapper {
    private CharacterMapper() {}

    public static CharacterEntity toEntity(Character character) {
        CharacterEntity entity = new CharacterEntity();
        entity.id = character.id();
        entity.setName(character.name());
        entity.setDescription(character.description());

        if (character.campaign() != null) {
            entity.setCampaign(CampaignMapper.toEntity(character.campaign()));
        }

        if (character instanceof PlayableCharacter playableCharacter) {
            if (playableCharacter.getPlayer() != null) {
                entity.setPlayer(PlayerMapper.toEntity(playableCharacter.getPlayer()));
            }
            if (playableCharacter.getCharacterClass() != null) {
                entity.setCharacterClass(CharacterClassMapper.toEntity(playableCharacter.getCharacterClass()));
            }
        }

        return entity;
    }

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
