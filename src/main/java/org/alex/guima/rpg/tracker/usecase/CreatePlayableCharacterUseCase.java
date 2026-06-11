package org.alex.guima.rpg.tracker.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.alex.guima.rpg.tracker.domain.exception.CampaignNotFoundException;
import org.alex.guima.rpg.tracker.domain.exception.CharacterClassNotFoundException;
import org.alex.guima.rpg.tracker.domain.exception.PlayerNotFoundException;
import org.alex.guima.rpg.tracker.domain.model.Campaign;
import org.alex.guima.rpg.tracker.domain.model.CharacterClass;
import org.alex.guima.rpg.tracker.domain.model.PlayableCharacter;
import org.alex.guima.rpg.tracker.domain.model.Player;
import org.alex.guima.rpg.tracker.domain.repository.CampaignRepository;
import org.alex.guima.rpg.tracker.domain.repository.CharacterClassRepository;
import org.alex.guima.rpg.tracker.domain.repository.CharacterRepository;
import org.alex.guima.rpg.tracker.domain.repository.PlayerRepository;

import java.util.UUID;

@ApplicationScoped
public class CreatePlayableCharacterUseCase {

    private final CharacterRepository characterRepository;
    private final CampaignRepository campaignRepository;
    private final PlayerRepository playerRepository;
    private final CharacterClassRepository characterClassRepository;

    @Inject
    public CreatePlayableCharacterUseCase(
            CharacterRepository characterRepository,
            CampaignRepository campaignRepository,
            PlayerRepository playerRepository,
            CharacterClassRepository characterClassRepository) {
        this.characterRepository = characterRepository;
        this.campaignRepository = campaignRepository;
        this.playerRepository = playerRepository;
        this.characterClassRepository = characterClassRepository;
    }

    public PlayableCharacter execute(CreatePlayableCharacterCommand command) {
        Campaign campaign = campaignRepository.findById(command.campaignId())
                .orElseThrow(() -> new CampaignNotFoundException(command.campaignId()));

        Player player = playerRepository.findById(command.playerId())
                .orElseThrow(() -> new PlayerNotFoundException(command.playerId()));

        CharacterClass characterClass = characterClassRepository.findById(command.characterClassId())
                .orElseThrow(() -> new CharacterClassNotFoundException(command.characterClassId()));

        PlayableCharacter character = new PlayableCharacter();
        character.setId(UUID.randomUUID());
        character.setName(command.name());
        character.setDescription(command.description());
        character.setCampaign(campaign);
        character.setPlayer(player);
        character.setCharacterClass(characterClass);

        return (PlayableCharacter) characterRepository.save(character);
    }
}
