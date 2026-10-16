package org.alex.guima.rpg.tracker.usecase;

import org.alex.guima.rpg.tracker.domain.model.CharacterClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreateCharacterClassUseCaseTest {

    @Test
    void execute_SavesCharacterClass() {
        FakeCharacterClassRepository repository = new FakeCharacterClassRepository();

        CharacterClass result = new CreateCharacterClassUseCase(repository)
                .execute(new CharacterClass(null, "Wizard", "Casts spells"));

        assertNotNull(result.id());
        assertEquals("Wizard", result.name());
        assertEquals("Casts spells", result.description());
    }
}
