package org.alex.guima.rpg.tracker.usecase;

import java.util.UUID;

public record CreateNonPlayableCharacterCommand(
    String name,
    String description,
    UUID campaignId
) {}
