package org.alex.guima.rpg.tracker.domain.repository;

import org.alex.guima.rpg.tracker.domain.model.Campaign;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CampaignRepository {
    Campaign save(Campaign campaign);

    Optional<Campaign> findById(UUID id);

    List<Campaign> findAll();
}
