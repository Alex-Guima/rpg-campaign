package org.alex.guima.rpg.tracker.usecase;

import org.alex.guima.rpg.tracker.domain.model.Campaign;
import org.alex.guima.rpg.tracker.domain.model.Character;
import org.alex.guima.rpg.tracker.domain.model.NonPlayableCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UpdateCharacterUseCaseTest {

    private FakeCharacterRepository repository;
    private UpdateCharacterUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = new FakeCharacterRepository();
        useCase = new UpdateCharacterUseCase(repository);
    }

    @Test
    void execute_WhenCharacterExists_UpdatesNameAndDescription() {
        Campaign campaign = new Campaign(UUID.randomUUID(), "Title", "Desc");
        Character saved = repository.save(new NonPlayableCharacter(null, "Old", "Old desc", campaign));

        Optional<Character> result = useCase.execute(saved.id(), "New", "New desc");

        assertTrue(result.isPresent());
        assertEquals("New", result.get().name());
        assertEquals("New desc", result.get().description());
        assertEquals(campaign, result.get().campaign());
    }

    @Test
    void execute_WhenCharacterDoesNotExist_ReturnsEmpty() {
        assertTrue(useCase.execute(UUID.randomUUID(), "New", "New desc").isEmpty());
    }
}
