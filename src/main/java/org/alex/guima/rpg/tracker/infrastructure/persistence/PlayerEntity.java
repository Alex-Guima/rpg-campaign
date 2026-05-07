package org.alex.guima.rpg.tracker.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "Player")
public class PlayerEntity extends PanacheEntity {

    private String name;

    @OneToMany
    private List<CharacterEntity> characters;
}
