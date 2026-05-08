package org.alex.guima.rpg.tracker.domain.repository;

import org.alex.guima.rpg.tracker.domain.model.Campaign;

import java.util.List;
import java.util.Optional;

public interface CampaignRepository {
    Campaign save(Campaign campaign);

    Optional<Campaign> findById(Long id);

    List<Campaign> findAll();
}
