package org.alex.guima.rpg.tracker.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.alex.guima.rpg.tracker.domain.model.Campaign;
import org.alex.guima.rpg.tracker.domain.repository.CampaignRepository;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UpdateCampaignUseCase {
    private final CampaignRepository repository;

    @Inject
    UpdateCampaignUseCase(CampaignRepository repository) {
        this.repository = repository;
    }

    public Optional<Campaign> execute(UUID id, Campaign updatedCampaign) {
        Optional<Campaign> existingOpt = repository.findById(id);
        if (existingOpt.isEmpty()) {
            return Optional.empty();
        }
        Campaign campaign = existingOpt.get();
        campaign.setTitle(updatedCampaign.title());
        campaign.setDescription(updatedCampaign.description());
        return Optional.of(repository.save(campaign));
    }
}
