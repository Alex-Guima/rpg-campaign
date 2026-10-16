package org.alex.guima.rpg.tracker.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.alex.guima.rpg.tracker.domain.model.Character;
import org.alex.guima.rpg.tracker.domain.repository.CharacterRepository;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UpdateCharacterUseCase {
    private final CharacterRepository repository;

    @Inject
    UpdateCharacterUseCase(CharacterRepository repository) {
        this.repository = repository;
    }

    public Optional<Character> execute(UUID id, String name, String description) {
        return repository.findById(id).map(character -> {
            character.setName(name);
            character.setDescription(description);
            return repository.save(character);
        });
    }
}
