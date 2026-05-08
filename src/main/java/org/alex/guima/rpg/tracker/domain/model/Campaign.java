package org.alex.guima.rpg.tracker.domain.model;

public class Campaign {

    public Campaign() {}

    public Campaign(String title, String description) {
        this.title = title;
        this.description = description;
    }

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
