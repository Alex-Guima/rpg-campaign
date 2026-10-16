package org.alex.guima.rpg.tracker.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.alex.guima.rpg.tracker.domain.model.CharacterClass;
import org.alex.guima.rpg.tracker.domain.repository.CharacterClassRepository;

@ApplicationScoped
public class CreateCharacterClassUseCase {
    private final CharacterClassRepository repository;

    @Inject
    CreateCharacterClassUseCase(CharacterClassRepository repository) {
        this.repository = repository;
    }

    public CharacterClass execute(CharacterClass characterClass) {
        return repository.save(characterClass);
    }
}
