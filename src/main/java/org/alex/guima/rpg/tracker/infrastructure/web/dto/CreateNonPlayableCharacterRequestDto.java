package org.alex.guima.rpg.tracker.infrastructure.web.dto;

import java.util.UUID;

public record CreateNonPlayableCharacterRequestDto(String name, String description, UUID campaignId) {
}
