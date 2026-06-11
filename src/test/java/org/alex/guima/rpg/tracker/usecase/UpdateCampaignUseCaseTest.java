package org.alex.guima.rpg.tracker.usecase;

import org.alex.guima.rpg.tracker.domain.model.Campaign;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UpdateCampaignUseCaseTest {

    private FakeCampaignRepository repository;
    private UpdateCampaignUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = new FakeCampaignRepository();
        useCase = new UpdateCampaignUseCase(repository);
    }

    @Test
    void execute_WhenCampaignExists_UpdatesAndReturnsCampaign() {
        Campaign campaign = new Campaign("Old Title", "Old Description");
        Campaign saved = repository.save(campaign);

        Campaign updatedInfo = new Campaign("New Title", "New Description");
        Optional<Campaign> result = useCase.execute(saved.id(), updatedInfo);

        assertTrue(result.isPresent());
        assertEquals(saved.id(), result.get().id());
        assertEquals("New Title", result.get().title());
        assertEquals("New Description", result.get().description());

        Campaign stored = repository.findById(saved.id()).orElse(null);
        assertEquals("New Title", stored.title());
        assertEquals("New Description", stored.description());
    }

    @Test
    void execute_WhenCampaignDoesNotExist_ReturnsEmpty() {
        Campaign updatedInfo = new Campaign("New Title", "New Description");
        Optional<Campaign> result = useCase.execute(UUID.randomUUID(), updatedInfo);

        assertTrue(result.isEmpty());
    }
}
