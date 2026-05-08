package org.alex.guima.rpg.tracker.infrastructure.persistence;

import org.alex.guima.rpg.tracker.domain.model.Campaign;

public class CampaignMapper {
    private CampaignMapper() {}

    public static CampaignEntity toEntity(Campaign campaign) {
        CampaignEntity entity = new CampaignEntity();
        entity.setTitle(campaign.title());
        entity.setDescription(campaign.description());

        return entity;
    }

    public static Campaign toDomain(CampaignEntity entity) {
        Campaign campaign = new Campaign();

        campaign.setTitle(entity.title());
        campaign.setDescription(entity.description());

        return campaign;
    }
}
