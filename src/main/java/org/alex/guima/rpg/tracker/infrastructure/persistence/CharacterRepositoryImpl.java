package org.alex.guima.rpg.tracker.infrastructure.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.alex.guima.rpg.tracker.domain.model.Character;
import org.alex.guima.rpg.tracker.domain.model.PlayableCharacter;
import org.alex.guima.rpg.tracker.domain.repository.CharacterRepository;

import java.util.List;
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
        CharacterEntity entity = character.id() == null ? null : repository.findById(character.id());
        if (entity == null) {
            entity = new CharacterEntity();
            repository.persist(entity);
        }
        EntityManager em = repository.getEntityManager();
        entity.setName(character.name());
        entity.setDescription(character.description());
        entity.setCampaign(character.campaign() == null ? null
                : em.find(CampaignEntity.class, character.campaign().id()));
        if (character instanceof PlayableCharacter playable) {
            entity.setPlayer(playable.getPlayer() == null ? null
                    : em.find(PlayerEntity.class, playable.getPlayer().id()));
            entity.setCharacterClass(playable.getCharacterClass() == null ? null
                    : em.find(CharacterClassEntity.class, playable.getCharacterClass().id()));
        }
        return CharacterMapper.toDomain(entity);
    }

    @Override
    public Optional<Character> findById(UUID id) {
        return repository.findByIdOptional(id).map(CharacterMapper::toDomain);
    }

    @Override
    public List<Character> findByCampaignId(UUID campaignId) {
        return repository.list("campaign.id", campaignId).stream()
                .map(CharacterMapper::toDomain)
                .toList();
    }
}
