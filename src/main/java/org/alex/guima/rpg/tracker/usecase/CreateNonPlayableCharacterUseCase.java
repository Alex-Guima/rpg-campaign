package org.alex.guima.rpg.tracker.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.alex.guima.rpg.tracker.domain.exception.CampaignNotFoundException;
import org.alex.guima.rpg.tracker.domain.model.Campaign;
import org.alex.guima.rpg.tracker.domain.model.NonPlayableCharacter;
import org.alex.guima.rpg.tracker.domain.repository.CampaignRepository;
import org.alex.guima.rpg.tracker.domain.repository.CharacterRepository;

import java.util.UUID;

@ApplicationScoped
public class CreateNonPlayableCharacterUseCase {

    private final CharacterRepository characterRepository;
    private final CampaignRepository campaignRepository;

    @Inject
    public CreateNonPlayableCharacterUseCase(
            CharacterRepository characterRepository,
            CampaignRepository campaignRepository) {
        this.characterRepository = characterRepository;
        this.campaignRepository = campaignRepository;
    }

    public NonPlayableCharacter execute(CreateNonPlayableCharacterCommand command) {
        Campaign campaign = campaignRepository.findById(command.campaignId())
                .orElseThrow(() -> new CampaignNotFoundException(command.campaignId()));

        NonPlayableCharacter character = new NonPlayableCharacter();
        character.setId(UUID.randomUUID());
        character.setName(command.name());
        character.setDescription(command.description());
        character.setCampaign(campaign);

        return (NonPlayableCharacter) characterRepository.save(character);
    }
}
