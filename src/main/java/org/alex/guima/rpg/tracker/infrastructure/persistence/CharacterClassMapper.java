package org.alex.guima.rpg.tracker.infrastructure.persistence;

import org.alex.guima.rpg.tracker.domain.model.CharacterClass;

public class CharacterClassMapper {
    private CharacterClassMapper() {}

    public static CharacterClassEntity toEntity(CharacterClass characterClass) {
        CharacterClassEntity entity = new CharacterClassEntity();
        entity.id = characterClass.id();
        entity.setName(characterClass.name());
        return entity;
    }

    public static CharacterClass toDomain(CharacterClassEntity entity) {
        if (entity == null) {
            return null;
        }
        return new CharacterClass(entity.id, entity.name());
    }
}
