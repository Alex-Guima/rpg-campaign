package org.alex.guima.rpg.tracker.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "Character")
public class CharacterEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue
    public UUID id;

    private String name;

    private String description;

    @ManyToOne
    private CampaignEntity campaign;

    @ManyToOne
    private PlayerEntity player;

    @ManyToOne
    private CharacterClassEntity characterClass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CampaignEntity getCampaign() {
        return campaign;
    }

    public void setCampaign(CampaignEntity campaign) {
        this.campaign = campaign;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public CharacterClassEntity getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClassEntity characterClass) {
        this.characterClass = characterClass;
    }
}
