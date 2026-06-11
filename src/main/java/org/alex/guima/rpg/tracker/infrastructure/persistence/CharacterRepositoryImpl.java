package org.alex.guima.rpg.tracker.infrastructure.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.alex.guima.rpg.tracker.domain.model.Character;
import org.alex.guima.rpg.tracker.domain.repository.CharacterRepository;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class CharacterRepositoryImpl implements CharacterRepository {

    private final PanacheCharacterRepository repository;

    @Inject
    public CharacterRepositoryImpl(PanacheCharacterRepository repository) {
        this.repository = repository;
    }

    @Override
    public Character save(Character character) {
        CharacterEntity entity = CharacterMapper.toEntity(character);
        if (entity.id == null) {
            repository.persist(entity);
        } else {
            repository.getEntityManager().merge(entity);
        }
        return CharacterMapper.toDomain(entity);
    }

    @Override
    public Optional<Character> findById(UUID id) {
        return repository.findByIdOptional(id).map(CharacterMapper::toDomain);
    }
}
