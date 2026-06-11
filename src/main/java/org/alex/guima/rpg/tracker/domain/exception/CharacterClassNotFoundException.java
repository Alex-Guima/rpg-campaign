package org.alex.guima.rpg.tracker.domain.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import java.util.UUID;

public class CharacterClassNotFoundException extends WebApplicationException {
    public CharacterClassNotFoundException(UUID id) {
        super("Character Class with id " + id + " not found", Response.Status.NOT_FOUND);
    }
}
