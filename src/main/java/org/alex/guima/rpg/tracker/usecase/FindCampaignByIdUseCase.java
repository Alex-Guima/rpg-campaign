package org.alex.guima.rpg.tracker.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.alex.guima.rpg.tracker.domain.model.Campaign;
import org.alex.guima.rpg.tracker.domain.repository.CampaignRepository;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class FindCampaignByIdUseCase {
    private final CampaignRepository repository;

    @Inject
    FindCampaignByIdUseCase(CampaignRepository repository) {
        this.repository = repository;
    }

    public Optional<Campaign> execute(UUID id) {
        return repository.findById(id);
    }
}
