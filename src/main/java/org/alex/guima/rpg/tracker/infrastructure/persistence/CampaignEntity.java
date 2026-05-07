package org.alex.guima.rpg.tracker.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "Campaign")
public class CampaignEntity extends PanacheEntity {
    private String title;

    private String description;

    @OneToMany
    private List<PlayerEntity> players;

    public String title() {
        return this.title;
    }

    public String description() {
        return this.description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
