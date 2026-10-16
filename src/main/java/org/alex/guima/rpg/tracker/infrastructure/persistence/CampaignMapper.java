package org.alex.guima.rpg.tracker.infrastructure.persistence;

import org.alex.guima.rpg.tracker.domain.model.Campaign;

public class CampaignMapper {
    private CampaignMapper() {}

    public static Campaign toDomain(CampaignEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Campaign(entity.id, entity.title(), entity.description());
    }
}
