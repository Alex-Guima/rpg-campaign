package org.alex.guima.rpg.tracker.domain.model;

import java.util.UUID;

public abstract class Character {

    private UUID id;

    String name;

    String description;

    Campaign campaign;

    public Character() {}

    public Character(UUID id, String name, String description, Campaign campaign) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.campaign = campaign;
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

    public String description() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Campaign campaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
}
