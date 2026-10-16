package org.alex.guima.rpg.tracker.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.alex.guima.rpg.tracker.domain.model.Player;
import org.alex.guima.rpg.tracker.domain.repository.PlayerRepository;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class FindPlayerByIdUseCase {
    private final PlayerRepository repository;

    @Inject
    FindPlayerByIdUseCase(PlayerRepository repository) {
        this.repository = repository;
    }

    public Optional<Player> execute(UUID id) {
        return repository.findById(id);
    }
}
