package org.alex.guima.rpg.tracker.domain.model;

import java.util.UUID;

public class Campaign {

    private UUID id;
    private String title;
    private String description;

    public Campaign() {}

    public Campaign(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Campaign(UUID id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public UUID id() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String description() {
        return description;
    }

    public String title() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
