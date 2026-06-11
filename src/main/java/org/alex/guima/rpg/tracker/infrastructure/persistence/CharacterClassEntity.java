package org.alex.guima.rpg.tracker.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "Character_class")
public class CharacterClassEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue
    public UUID id;

    private String name;

    private String description;

    public String name() {
        return this.name;
    }

    public String description() {
        return this.description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
