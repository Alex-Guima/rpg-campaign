package org.alex.guima.rpg.tracker.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.alex.guima.rpg.tracker.domain.model.Player;
import org.alex.guima.rpg.tracker.domain.repository.PlayerRepository;

@ApplicationScoped
public class CreatePlayerUseCase {
    private final PlayerRepository repository;

    @Inject
    CreatePlayerUseCase(PlayerRepository repository) {
        this.repository = repository;
    }

    public Player execute(Player player) {
        return repository.save(player);
    }
}
