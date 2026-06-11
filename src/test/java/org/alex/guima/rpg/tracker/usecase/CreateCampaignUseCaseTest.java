package org.alex.guima.rpg.tracker.usecase;

import org.alex.guima.rpg.tracker.domain.model.Campaign;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreateCampaignUseCaseTest {

    private FakeCampaignRepository repository;
    private CreateCampaignUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = new FakeCampaignRepository();
        useCase = new CreateCampaignUseCase(repository);
    }

    @Test
    void execute() {
        Campaign campaign = new Campaign("D&D Campaign", "A great adventure");
        Campaign result = useCase.execute(campaign);

        assertNotNull(result.id());
        assertEquals("D&D Campaign", result.title());
        assertEquals("A great adventure", result.description());

        Campaign saved = repository.findById(result.id()).orElse(null);
        assertNotNull(saved);
        assertEquals(result.id(), saved.id());
    }
}