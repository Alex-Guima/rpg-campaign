package org.alex.guima.rpg.tracker.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class PlayableCharacter extends Character {

    @OneToOne
    private CharacterClass characterClass;

    @ManyToOne
    private Player player;

    public CharacterClass getCharacterClass() {
        return characterClass;
    }
}
