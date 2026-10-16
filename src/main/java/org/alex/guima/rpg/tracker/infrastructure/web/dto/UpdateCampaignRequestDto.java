package org.alex.guima.rpg.tracker.infrastructure.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCampaignRequestDto(
        @NotBlank @Size(max = 64) String title,
        @Size(max = 255) String description) {
}
