package org.alex.guima.rpg.tracker.domain.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class CharacterClass extends PanacheEntity {

    private String name;

    public String getName() {
        return name;
    }
}
