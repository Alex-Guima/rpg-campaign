package org.alex.guima.rpg.tracker.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.alex.guima.rpg.tracker.domain.model.Player;
import org.alex.guima.rpg.tracker.domain.repository.PlayerRepository;

import java.util.List;

@ApplicationScoped
public class ListPlayersUseCase {
    private final PlayerRepository repository;

    @Inject
    ListPlayersUseCase(PlayerRepository repository) {
        this.repository = repository;
    }

    public List<Player> execute() {
        return repository.findAll();
    }
}
