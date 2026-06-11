package org.alex.guima.rpg.tracker.usecase;

import org.alex.guima.rpg.tracker.domain.model.Campaign;
import org.alex.guima.rpg.tracker.domain.repository.CampaignRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class FakeCampaignRepository implements CampaignRepository {
    private final Map<UUID, Campaign> store = new HashMap<>();

    @Override
    public Campaign save(Campaign campaign) {
        if (campaign.id() == null) {
            campaign.setId(UUID.randomUUID());
        }
        store.put(campaign.id(), campaign);
        return campaign;
    }

    @Override
    public Optional<Campaign> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Campaign> findAll() {
        return new ArrayList<>(store.values());
    }
}
