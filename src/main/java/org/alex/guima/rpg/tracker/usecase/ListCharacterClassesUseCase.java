package org.alex.guima.rpg.tracker.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.alex.guima.rpg.tracker.domain.model.CharacterClass;
import org.alex.guima.rpg.tracker.domain.repository.CharacterClassRepository;

import java.util.List;

@ApplicationScoped
public class ListCharacterClassesUseCase {
    private final CharacterClassRepository repository;

    @Inject
    ListCharacterClassesUseCase(CharacterClassRepository repository) {
        this.repository = repository;
    }

    public List<CharacterClass> execute() {
        return repository.findAll();
    }
}
