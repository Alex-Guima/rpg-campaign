package org.alex.guima.rpg.tracker.usecase;

import java.util.UUID;

public record CreatePlayableCharacterCommand(
    String name,
    String description,
    UUID campaignId,
    UUID playerId,
    UUID characterClassId
) {}
