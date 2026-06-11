package org.alex.guima.rpg.tracker.domain.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import java.util.UUID;

public class PlayerNotFoundException extends WebApplicationException {
    public PlayerNotFoundException(UUID id) {
        super("Player with id " + id + " not found", Response.Status.NOT_FOUND);
    }
}
