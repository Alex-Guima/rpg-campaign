package org.alex.guima.rpg.tracker.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.alex.guima.rpg.tracker.domain.exception.CampaignNotFoundException;
import org.alex.guima.rpg.tracker.domain.model.Character;
import org.alex.guima.rpg.tracker.domain.repository.CampaignRepository;
import org.alex.guima.rpg.tracker.domain.repository.CharacterRepository;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ListCharactersByCampaignUseCase {
    private final CharacterRepository characterRepository;
    private final CampaignRepository campaignRepository;

    @Inject
    ListCharactersByCampaignUseCase(CharacterRepository characterRepository, CampaignRepository campaignRepository) {
        this.characterRepository = characterRepository;
        this.campaignRepository = campaignRepository;
    }

    public List<Character> execute(UUID campaignId) {
        if (campaignRepository.findById(campaignId).isEmpty()) {
            throw new CampaignNotFoundException(campaignId);
        }
        return characterRepository.findByCampaignId(campaignId);
    }
}
