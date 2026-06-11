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
@Table(name = "Campaign")
public class CampaignEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue
    public UUID id;

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
