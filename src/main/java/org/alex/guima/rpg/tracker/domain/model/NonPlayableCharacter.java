package org.alex.guima.rpg.tracker.domain.model;

import java.util.UUID;

public class NonPlayableCharacter extends Character {

    public NonPlayableCharacter() {
        super();
    }

    public NonPlayableCharacter(UUID id, String name, String description, Campaign campaign) {
        super(id, name, description, campaign);
    }
}
