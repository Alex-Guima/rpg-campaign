package org.alex.guima.rpg.tracker.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.alex.guima.rpg.tracker.domain.model.Campaign;
import org.alex.guima.rpg.tracker.domain.repository.CampaignRepository;

@ApplicationScoped
public class CreateCampaignUseCase {
    private final CampaignRepository repository;

    @Inject
    CreateCampaignUseCase(CampaignRepository repository) {
        this.repository = repository;
    }

    public Campaign execute(Campaign campaign) {
        return repository.save(campaign);
    }
}
