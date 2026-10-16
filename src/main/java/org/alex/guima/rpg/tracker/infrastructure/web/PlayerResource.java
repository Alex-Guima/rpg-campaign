package org.alex.guima.rpg.tracker.infrastructure.web;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.alex.guima.rpg.tracker.domain.model.Player;
import org.alex.guima.rpg.tracker.infrastructure.web.dto.PlayerRequestDto;
import org.alex.guima.rpg.tracker.infrastructure.web.dto.PlayerResponseDto;
import org.alex.guima.rpg.tracker.usecase.CreatePlayerUseCase;
import org.alex.guima.rpg.tracker.usecase.FindPlayerByIdUseCase;
import org.alex.guima.rpg.tracker.usecase.ListPlayersUseCase;

import java.util.List;
import java.util.UUID;

@Path("/player")
public class PlayerResource {

    private final CreatePlayerUseCase createPlayerUseCase;
    private final FindPlayerByIdUseCase findPlayerByIdUseCase;
    private final ListPlayersUseCase listPlayersUseCase;

    @Inject
    public PlayerResource(
            CreatePlayerUseCase createPlayerUseCase,
            FindPlayerByIdUseCase findPlayerByIdUseCase,
            ListPlayersUseCase listPlayersUseCase) {
        this.createPlayerUseCase = createPlayerUseCase;
        this.findPlayerByIdUseCase = findPlayerByIdUseCase;
        this.listPlayersUseCase = listPlayersUseCase;
    }

    @Transactional
    @POST
    public Response create(@Valid @NotNull PlayerRequestDto request) {
        Player created = createPlayerUseCase.execute(new Player(null, request.name()));
        return Response.status(Response.Status.CREATED).entity(PlayerResponseDto.from(created)).build();
    }

    @GET
    public List<PlayerResponseDto> list() {
        return listPlayersUseCase.execute().stream().map(PlayerResponseDto::from).toList();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") UUID id) {
        return findPlayerByIdUseCase.execute(id)
                .map(player -> Response.ok(PlayerResponseDto.from(player)).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }
}
