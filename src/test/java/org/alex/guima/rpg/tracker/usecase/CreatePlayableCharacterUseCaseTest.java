package org.alex.guima.rpg.tracker.usecase;

import org.alex.guima.rpg.tracker.domain.exception.CampaignNotFoundException;
import org.alex.guima.rpg.tracker.domain.exception.CharacterClassNotFoundException;
import org.alex.guima.rpg.tracker.domain.exception.PlayerNotFoundException;
import org.alex.guima.rpg.tracker.domain.model.Campaign;
import org.alex.guima.rpg.tracker.domain.model.CharacterClass;
import org.alex.guima.rpg.tracker.domain.model.PlayableCharacter;
import org.alex.guima.rpg.tracker.domain.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreatePlayableCharacterUseCaseTest {

    private CreatePlayableCharacterUseCase useCase;
    private FakeCampaignRepository campaignRepository;
    private FakePlayerRepository playerRepository;
    private FakeCharacterClassRepository characterClassRepository;
    private FakeCharacterRepository characterRepository;

    @BeforeEach
    void setUp() {
        campaignRepository = new FakeCampaignRepository();
        playerRepository = new FakePlayerRepository();
        characterClassRepository = new FakeCharacterClassRepository();
        characterRepository = new FakeCharacterRepository();
        useCase = new CreatePlayableCharacterUseCase(
                characterRepository, campaignRepository, playerRepository, characterClassRepository);
    }

    @Test
    void execute_ShouldCreatePlayableCharacter() {
        Campaign campaign = new Campaign(UUID.randomUUID(), "Campaign Title", "Desc");
        campaignRepository.save(campaign);

        Player player = new Player(UUID.randomUUID(), "Player Name");
        playerRepository.addPlayer(player);

        CharacterClass characterClass = new CharacterClass(UUID.randomUUID(), "Warrior");
        characterClassRepository.addCharacterClass(characterClass);

        CreatePlayableCharacterCommand command = new CreatePlayableCharacterCommand(
                "Grom", "A strong warrior", campaign.id(), player.id(), characterClass.id());

        PlayableCharacter result = useCase.execute(command);

        assertNotNull(result.id());
        assertEquals("Grom", result.name());
        assertEquals("A strong warrior", result.description());
        assertEquals(campaign, result.campaign());
        assertEquals(player, result.getPlayer());
        assertEquals(characterClass, result.getCharacterClass());
    }

    @Test
    void execute_ShouldThrowWhenCampaignNotFound() {
        CreatePlayableCharacterCommand command = new CreatePlayableCharacterCommand(
                "Grom", "Desc", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

        assertThrows(CampaignNotFoundException.class, () -> useCase.execute(command));
    }

    @Test
    void execute_ShouldThrowWhenPlayerNotFound() {
        Campaign campaign = new Campaign(UUID.randomUUID(), "Campaign Title", "Desc");
        campaignRepository.save(campaign);

        CreatePlayableCharacterCommand command = new CreatePlayableCharacterCommand(
                "Grom", "Desc", campaign.id(), UUID.randomUUID(), UUID.randomUUID());

        assertThrows(PlayerNotFoundException.class, () -> useCase.execute(command));
    }

    @Test
    void execute_ShouldThrowWhenCharacterClassNotFound() {
        Campaign campaign = new Campaign(UUID.randomUUID(), "Campaign Title", "Desc");
        campaignRepository.save(campaign);

        Player player = new Player(UUID.randomUUID(), "Player Name");
        playerRepository.addPlayer(player);

        CreatePlayableCharacterCommand command = new CreatePlayableCharacterCommand(
                "Grom", "Desc", campaign.id(), player.id(), UUID.randomUUID());

        assertThrows(CharacterClassNotFoundException.class, () -> useCase.execute(command));
    }
}
