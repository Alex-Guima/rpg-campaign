package org.alex.guima.rpg.tracker.domain.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Campaign extends PanacheEntity {

    private String title;

    private String description;

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}
