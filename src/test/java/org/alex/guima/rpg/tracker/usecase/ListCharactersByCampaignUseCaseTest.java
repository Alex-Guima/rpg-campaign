package org.alex.guima.rpg.tracker.usecase;

import org.alex.guima.rpg.tracker.domain.exception.CampaignNotFoundException;
import org.alex.guima.rpg.tracker.domain.model.Campaign;
import org.alex.guima.rpg.tracker.domain.model.Character;
import org.alex.guima.rpg.tracker.domain.model.NonPlayableCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListCharactersByCampaignUseCaseTest {

    private FakeCampaignRepository campaignRepository;
    private FakeCharacterRepository characterRepository;
    private ListCharactersByCampaignUseCase useCase;

    @BeforeEach
    void setUp() {
        campaignRepository = new FakeCampaignRepository();
        characterRepository = new FakeCharacterRepository();
        useCase = new ListCharactersByCampaignUseCase(characterRepository, campaignRepository);
    }

    @Test
    void execute_ReturnsOnlyCharactersOfCampaign() {
        Campaign campaign = campaignRepository.save(new Campaign("A", "A"));
        Campaign other = campaignRepository.save(new Campaign("B", "B"));
        characterRepository.save(new NonPlayableCharacter(null, "Innkeeper", "Desc", campaign));
        characterRepository.save(new NonPlayableCharacter(null, "Guard", "Desc", other));

        List<Character> result = useCase.execute(campaign.id());

        assertEquals(1, result.size());
        assertEquals("Innkeeper", result.get(0).name());
    }

    @Test
    void execute_ThrowsWhenCampaignNotFound() {
        assertThrows(CampaignNotFoundException.class, () -> useCase.execute(UUID.randomUUID()));
    }
}
