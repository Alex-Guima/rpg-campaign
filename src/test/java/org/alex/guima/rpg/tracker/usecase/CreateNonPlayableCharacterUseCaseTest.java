package org.alex.guima.rpg.tracker.usecase;

import org.alex.guima.rpg.tracker.domain.exception.CampaignNotFoundException;
import org.alex.guima.rpg.tracker.domain.model.Campaign;
import org.alex.guima.rpg.tracker.domain.model.NonPlayableCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateNonPlayableCharacterUseCaseTest {

    private CreateNonPlayableCharacterUseCase useCase;
    private FakeCampaignRepository campaignRepository;
    private FakeCharacterRepository characterRepository;

    @BeforeEach
    void setUp() {
        campaignRepository = new FakeCampaignRepository();
        characterRepository = new FakeCharacterRepository();
        useCase = new CreateNonPlayableCharacterUseCase(characterRepository, campaignRepository);
    }

    @Test
    void execute_ShouldCreateNonPlayableCharacter() {
        Campaign campaign = new Campaign(UUID.randomUUID(), "Campaign Title", "Desc");
        campaignRepository.save(campaign);

        CreateNonPlayableCharacterCommand command = new CreateNonPlayableCharacterCommand(
                "Bob", "A merchant", campaign.id());

        NonPlayableCharacter result = useCase.execute(command);

        assertNotNull(result.id());
        assertEquals("Bob", result.name());
        assertEquals("A merchant", result.description());
        assertEquals(campaign, result.campaign());
    }

    @Test
    void execute_ShouldThrowWhenCampaignNotFound() {
        CreateNonPlayableCharacterCommand command = new CreateNonPlayableCharacterCommand(
                "Bob", "Desc", UUID.randomUUID());

        assertThrows(CampaignNotFoundException.class, () -> useCase.execute(command));
    }
}
