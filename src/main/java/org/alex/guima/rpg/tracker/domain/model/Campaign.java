package org.alex.guima.rpg.tracker.domain.model;

public class Campaign {

    private String title;

    private String description;

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
