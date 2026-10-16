package org.alex.guima.rpg.tracker.infrastructure.web.dto;

import java.util.UUID;

public record CreatePlayableCharacterRequestDto(
        String name,
        String description,
        UUID campaignId,
        UUID playerId,
        UUID characterClassId) {
}
