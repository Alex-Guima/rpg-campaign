package org.alex.guima.rpg.tracker.infrastructure.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.alex.guima.rpg.tracker.domain.model.CharacterClass;
import org.alex.guima.rpg.tracker.domain.repository.CharacterClassRepository;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class CharacterClassRepositoryImpl implements CharacterClassRepository {

    private final PanacheCharacterClassRepository repository;

    @Inject
    public CharacterClassRepositoryImpl(PanacheCharacterClassRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<CharacterClass> findById(UUID id) {
        return repository.findByIdOptional(id).map(CharacterClassMapper::toDomain);
    }
}
