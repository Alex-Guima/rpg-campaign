package org.alex.guima.rpg.tracker.infrastructure.web.dto;

import java.util.UUID;

public record CampaignResponseDto(UUID id, String title, String description) {
}
