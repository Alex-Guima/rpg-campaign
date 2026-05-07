package org.alex.guima.rpg.tracker.domain.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;

public class Player {

    private String name;

    public String name() {
        return name;
    }
}
