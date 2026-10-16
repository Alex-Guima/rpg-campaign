package org.alex.guima.rpg.tracker.infrastructure.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateNonPlayableCharacterRequestDto(
        @NotBlank @Size(max = 64) String name,
        @Size(max = 255) String description,
        @NotNull UUID campaignId) {
}
