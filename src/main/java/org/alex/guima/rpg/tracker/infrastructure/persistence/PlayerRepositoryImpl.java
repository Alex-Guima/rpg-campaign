package org.alex.guima.rpg.tracker.infrastructure.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.alex.guima.rpg.tracker.domain.model.Player;
import org.alex.guima.rpg.tracker.domain.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class PlayerRepositoryImpl implements PlayerRepository {

    private final PanachePlayerRepository repository;

    @Inject
    public PlayerRepositoryImpl(PanachePlayerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Player save(Player player) {
        PlayerEntity entity = player.id() == null ? null : repository.findById(player.id());
        if (entity == null) {
            entity = new PlayerEntity();
            repository.persist(entity);
        }
        entity.setName(player.name());
        return PlayerMapper.toDomain(entity);
    }

    @Override
    public Optional<Player> findById(UUID id) {
        return repository.findByIdOptional(id).map(PlayerMapper::toDomain);
    }

    @Override
    public List<Player> findAll() {
        return repository.listAll().stream()
                .map(PlayerMapper::toDomain)
                .toList();
    }
}
