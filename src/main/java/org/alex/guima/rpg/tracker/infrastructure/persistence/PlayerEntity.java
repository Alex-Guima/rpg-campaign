package org.alex.guima.rpg.tracker.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Player")
public class PlayerEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue
    public UUID id;

    private String name;

    @OneToMany
    private List<CharacterEntity> characters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CharacterEntity> getCharacters() {
        return characters;
    }

    public void setCharacters(List<CharacterEntity> characters) {
        this.characters = characters;
    }
}
