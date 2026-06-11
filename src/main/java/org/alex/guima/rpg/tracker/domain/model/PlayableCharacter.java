package org.alex.guima.rpg.tracker.domain.model;

import java.util.UUID;

public class PlayableCharacter extends Character {

    private CharacterClass characterClass;

    private Player player;

    public PlayableCharacter() {
        super();
    }

    public PlayableCharacter(UUID id, String name, String description, Campaign campaign, CharacterClass characterClass, Player player) {
        super(id, name, description, campaign);
        this.characterClass = characterClass;
        this.player = player;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
