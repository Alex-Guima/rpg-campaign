package org.alex.guima.rpg.tracker.usecase;

import org.alex.guima.rpg.tracker.domain.model.Campaign;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FindCampaignByIdUseCaseTest {

    private FakeCampaignRepository repository;
    private FindCampaignByIdUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = new FakeCampaignRepository();
        useCase = new FindCampaignByIdUseCase(repository);
    }

    @Test
    void execute_WhenCampaignExists_ReturnsCampaign() {
        Campaign campaign = new Campaign("D&D Campaign", "A great adventure");
        Campaign saved = repository.save(campaign);

        Optional<Campaign> result = useCase.execute(saved.id());

        assertTrue(result.isPresent());
        assertEquals(saved.id(), result.get().id());
        assertEquals("D&D Campaign", result.get().title());
    }

    @Test
    void execute_WhenCampaignDoesNotExist_ReturnsEmpty() {
        Optional<Campaign> result = useCase.execute(UUID.randomUUID());

        assertTrue(result.isEmpty());
    }
}
