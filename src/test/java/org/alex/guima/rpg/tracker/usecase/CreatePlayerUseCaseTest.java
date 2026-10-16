package org.alex.guima.rpg.tracker.usecase;

import org.alex.guima.rpg.tracker.domain.model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreatePlayerUseCaseTest {

    @Test
    void execute_SavesPlayer() {
        FakePlayerRepository repository = new FakePlayerRepository();

        Player result = new CreatePlayerUseCase(repository).execute(new Player(null, "Alex"));

        assertNotNull(result.id());
        assertEquals("Alex", result.name());
        assertEquals(result, repository.findById(result.id()).orElse(null));
    }
}
