package org.alex.guima.rpg.tracker.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.alex.guima.rpg.tracker.domain.model.CharacterClass;
import org.alex.guima.rpg.tracker.domain.repository.CharacterClassRepository;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class FindCharacterClassByIdUseCase {
    private final CharacterClassRepository repository;

    @Inject
    FindCharacterClassByIdUseCase(CharacterClassRepository repository) {
        this.repository = repository;
    }

    public Optional<CharacterClass> execute(UUID id) {
        return repository.findById(id);
    }
}
