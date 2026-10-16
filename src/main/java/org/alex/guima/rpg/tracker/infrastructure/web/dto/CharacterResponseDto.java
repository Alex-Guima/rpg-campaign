package org.alex.guima.rpg.tracker.infrastructure.web.dto;

import org.alex.guima.rpg.tracker.domain.model.Character;
import org.alex.guima.rpg.tracker.domain.model.PlayableCharacter;

import java.util.UUID;

public record CharacterResponseDto(
        UUID id,
        String type,
        String name,
        String description,
        UUID campaignId,
        UUID playerId,
        UUID characterClassId) {

    public static CharacterResponseDto from(Character character) {
        UUID campaignId = character.campaign() == null ? null : character.campaign().id();
        if (character instanceof PlayableCharacter playable) {
            return new CharacterResponseDto(
                    playable.id(),
                    "PLAYABLE",
                    playable.name(),
                    playable.description(),
                    campaignId,
                    playable.getPlayer() == null ? null : playable.getPlayer().id(),
                    playable.getCharacterClass() == null ? null : playable.getCharacterClass().id());
        }
        return new CharacterResponseDto(
                character.id(), "NON_PLAYABLE", character.name(), character.description(), campaignId, null, null);
    }
}
