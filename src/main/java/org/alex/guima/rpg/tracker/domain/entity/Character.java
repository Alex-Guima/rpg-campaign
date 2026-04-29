package org.alex.guima.rpg.tracker.domain.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;

@MappedSuperclass
public abstract class Character extends PanacheEntity {

    String name;

    String description;

    @OneToOne
    Campaign campaign;

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

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
}
