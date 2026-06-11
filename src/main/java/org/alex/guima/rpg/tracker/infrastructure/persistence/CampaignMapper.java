package org.alex.guima.rpg.tracker.infrastructure.persistence;

import org.alex.guima.rpg.tracker.domain.model.Campaign;

public class CampaignMapper {
    private CampaignMapper() {}

    public static CampaignEntity toEntity(Campaign campaign) {
        CampaignEntity entity = new CampaignEntity();
        entity.id = campaign.id();
        entity.setTitle(campaign.title());
        entity.setDescription(campaign.description());

        return entity;
    }

    public static Campaign toDomain(CampaignEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Campaign(entity.id, entity.title(), entity.description());
    }
}
