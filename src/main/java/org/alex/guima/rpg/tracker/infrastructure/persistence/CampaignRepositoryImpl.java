package org.alex.guima.rpg.tracker.infrastructure.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.alex.guima.rpg.tracker.domain.model.Campaign;
import org.alex.guima.rpg.tracker.domain.repository.CampaignRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class CampaignRepositoryImpl implements CampaignRepository {

    private final PanacheCampaignRepository repository;

    @Inject
    CampaignRepositoryImpl(PanacheCampaignRepository repository) {
        this.repository = repository;
    }

    @Override
    public Campaign save(Campaign campaign) {
        CampaignEntity entity = campaign.id() == null ? null : repository.findById(campaign.id());
        if (entity == null) {
            entity = new CampaignEntity();
            repository.persist(entity);
        }
        entity.setTitle(campaign.title());
        entity.setDescription(campaign.description());
        return CampaignMapper.toDomain(entity);
    }

    @Override
    public Optional<Campaign> findById(UUID id) {
        return repository.findByIdOptional(id).map(CampaignMapper::toDomain);
    }

    @Override
    public List<Campaign> findAll() {
        return repository.listAll().stream()
                .map(CampaignMapper::toDomain)
                .toList();
    }
}
