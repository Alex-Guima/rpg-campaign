package org.alex.guima.rpg.tracker.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class PanacheCampaignRepository implements PanacheRepositoryBase<CampaignEntity, UUID> {
}
