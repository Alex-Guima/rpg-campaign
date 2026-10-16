package org.alex.guima.rpg.tracker.infrastructure.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.alex.guima.rpg.tracker.domain.model.CharacterClass;
import org.alex.guima.rpg.tracker.domain.repository.CharacterClassRepository;

import java.util.List;
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
    public CharacterClass save(CharacterClass characterClass) {
        CharacterClassEntity entity = characterClass.id() == null ? null : repository.findById(characterClass.id());
        if (entity == null) {
            entity = new CharacterClassEntity();
            repository.persist(entity);
        }
        entity.setName(characterClass.name());
        entity.setDescription(characterClass.description());
        return CharacterClassMapper.toDomain(entity);
    }

    @Override
    public Optional<CharacterClass> findById(UUID id) {
        return repository.findByIdOptional(id).map(CharacterClassMapper::toDomain);
    }

    @Override
    public List<CharacterClass> findAll() {
        return repository.listAll().stream()
                .map(CharacterClassMapper::toDomain)
                .toList();
    }
}
