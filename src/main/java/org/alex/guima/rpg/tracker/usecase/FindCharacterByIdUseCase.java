package org.alex.guima.rpg.tracker.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.alex.guima.rpg.tracker.domain.model.Character;
import org.alex.guima.rpg.tracker.domain.repository.CharacterRepository;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class FindCharacterByIdUseCase {
    private final CharacterRepository repository;

    @Inject
    FindCharacterByIdUseCase(CharacterRepository repository) {
        this.repository = repository;
    }

    public Optional<Character> execute(UUID id) {
        return repository.findById(id);
    }
}
