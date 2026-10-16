package org.alex.guima.rpg.tracker.infrastructure.web;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.alex.guima.rpg.tracker.domain.model.CharacterClass;
import org.alex.guima.rpg.tracker.infrastructure.web.dto.CharacterClassRequestDto;
import org.alex.guima.rpg.tracker.infrastructure.web.dto.CharacterClassResponseDto;
import org.alex.guima.rpg.tracker.usecase.CreateCharacterClassUseCase;
import org.alex.guima.rpg.tracker.usecase.FindCharacterClassByIdUseCase;
import org.alex.guima.rpg.tracker.usecase.ListCharacterClassesUseCase;

import java.util.List;
import java.util.UUID;

@Path("/character-class")
public class CharacterClassResource {

    private final CreateCharacterClassUseCase createCharacterClassUseCase;
    private final FindCharacterClassByIdUseCase findCharacterClassByIdUseCase;
    private final ListCharacterClassesUseCase listCharacterClassesUseCase;

    @Inject
    public CharacterClassResource(
            CreateCharacterClassUseCase createCharacterClassUseCase,
            FindCharacterClassByIdUseCase findCharacterClassByIdUseCase,
            ListCharacterClassesUseCase listCharacterClassesUseCase) {
        this.createCharacterClassUseCase = createCharacterClassUseCase;
        this.findCharacterClassByIdUseCase = findCharacterClassByIdUseCase;
        this.listCharacterClassesUseCase = listCharacterClassesUseCase;
    }

    @Transactional
    @POST
    public Response create(CharacterClassRequestDto request) {
        CharacterClass created = createCharacterClassUseCase.execute(
                new CharacterClass(null, request.name(), request.description()));
        return Response.status(Response.Status.CREATED).entity(CharacterClassResponseDto.from(created)).build();
    }

    @GET
    public List<CharacterClassResponseDto> list() {
        return listCharacterClassesUseCase.execute().stream().map(CharacterClassResponseDto::from).toList();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") UUID id) {
        return findCharacterClassByIdUseCase.execute(id)
                .map(characterClass -> Response.ok(CharacterClassResponseDto.from(characterClass)).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }
}
