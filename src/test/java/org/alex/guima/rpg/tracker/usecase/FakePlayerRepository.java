package org.alex.guima.rpg.tracker.usecase;

import org.alex.guima.rpg.tracker.domain.model.Player;
import org.alex.guima.rpg.tracker.domain.repository.PlayerRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class FakePlayerRepository implements PlayerRepository {
    private final Map<UUID, Player> store = new HashMap<>();

    public void addPlayer(Player player) {
        store.put(player.id(), player);
    }

    @Override
    public Optional<Player> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }
}
