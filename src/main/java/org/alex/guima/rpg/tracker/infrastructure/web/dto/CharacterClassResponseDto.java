package org.alex.guima.rpg.tracker.infrastructure.web.dto;

import org.alex.guima.rpg.tracker.domain.model.CharacterClass;

import java.util.UUID;

public record CharacterClassResponseDto(UUID id, String name, String description) {
    public static CharacterClassResponseDto from(CharacterClass characterClass) {
        return new CharacterClassResponseDto(characterClass.id(), characterClass.name(), characterClass.description());
    }
}
