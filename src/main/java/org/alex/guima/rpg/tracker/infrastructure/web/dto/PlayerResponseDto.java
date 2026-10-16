package org.alex.guima.rpg.tracker.infrastructure.web.dto;

import org.alex.guima.rpg.tracker.domain.model.Player;

import java.util.UUID;

public record PlayerResponseDto(UUID id, String name) {
    public static PlayerResponseDto from(Player player) {
        return new PlayerResponseDto(player.id(), player.name());
    }
}
