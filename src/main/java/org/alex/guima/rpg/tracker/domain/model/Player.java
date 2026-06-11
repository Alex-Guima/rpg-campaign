package org.alex.guima.rpg.tracker.domain.model;

import java.util.UUID;

public class Player {

    private UUID id;

    private String name;

    public Player() {}

    public Player(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID id() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
