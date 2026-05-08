package org.alex.guima.rpg.tracker.infrastructure.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.alex.guima.rpg.tracker.domain.model.Campaign;
import org.alex.guima.rpg.tracker.domain.repository.CampaignRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CampaignRepositoryImpl implements CampaignRepository {

    private final PanacheCampaignRepository repository;

    @Inject
    CampaignRepositoryImpl(PanacheCampaignRepository repository) {
        this.repository = repository;
    }

    @Override
    public Campaign save(Campaign campaign) {
        CampaignEntity entity = CampaignMapper.toEntity(campaign);
        repository.persist(entity);
        return CampaignMapper.toDomain(entity);
    }

    @Override
    public Optional<Campaign> findById(Long id) {
        return repository.findByIdOptional(id).map(CampaignMapper::toDomain);
    }

    @Override
    public List<Campaign> findAll() {
        return repository.findAll().project(Campaign.class).list();
    }
}
