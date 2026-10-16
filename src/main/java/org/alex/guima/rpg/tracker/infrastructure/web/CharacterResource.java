package org.alex.guima.rpg.tracker.infrastructure.web;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.alex.guima.rpg.tracker.infrastructure.web.dto.CharacterResponseDto;
import org.alex.guima.rpg.tracker.infrastructure.web.dto.CreateNonPlayableCharacterRequestDto;
import org.alex.guima.rpg.tracker.infrastructure.web.dto.CreatePlayableCharacterRequestDto;
import org.alex.guima.rpg.tracker.infrastructure.web.dto.UpdateCharacterRequestDto;
import org.alex.guima.rpg.tracker.usecase.CreateNonPlayableCharacterCommand;
import org.alex.guima.rpg.tracker.usecase.CreateNonPlayableCharacterUseCase;
import org.alex.guima.rpg.tracker.usecase.CreatePlayableCharacterCommand;
import org.alex.guima.rpg.tracker.usecase.CreatePlayableCharacterUseCase;
import org.alex.guima.rpg.tracker.usecase.FindCharacterByIdUseCase;
import org.alex.guima.rpg.tracker.usecase.ListCharactersByCampaignUseCase;
import org.alex.guima.rpg.tracker.usecase.UpdateCharacterUseCase;

import java.util.List;
import java.util.UUID;

@Path("/character")
public class CharacterResource {

    private final CreateNonPlayableCharacterUseCase createNonPlayableCharacterUseCase;
    private final CreatePlayableCharacterUseCase createPlayableCharacterUseCase;
    private final FindCharacterByIdUseCase findCharacterByIdUseCase;
    private final ListCharactersByCampaignUseCase listCharactersByCampaignUseCase;
    private final UpdateCharacterUseCase updateCharacterUseCase;

    @Inject
    public CharacterResource(
            CreateNonPlayableCharacterUseCase createNonPlayableCharacterUseCase,
            CreatePlayableCharacterUseCase createPlayableCharacterUseCase,
            FindCharacterByIdUseCase findCharacterByIdUseCase,
            ListCharactersByCampaignUseCase listCharactersByCampaignUseCase,
            UpdateCharacterUseCase updateCharacterUseCase) {
        this.createNonPlayableCharacterUseCase = createNonPlayableCharacterUseCase;
        this.createPlayableCharacterUseCase = createPlayableCharacterUseCase;
        this.findCharacterByIdUseCase = findCharacterByIdUseCase;
        this.listCharactersByCampaignUseCase = listCharactersByCampaignUseCase;
        this.updateCharacterUseCase = updateCharacterUseCase;
    }

    @Transactional
    @POST
    @Path("/non-playable")
    public Response createNonPlayable(@Valid @NotNull CreateNonPlayableCharacterRequestDto request) {
        var created = createNonPlayableCharacterUseCase.execute(new CreateNonPlayableCharacterCommand(
                request.name(), request.description(), request.campaignId()));
        return Response.status(Response.Status.CREATED).entity(CharacterResponseDto.from(created)).build();
    }

    @Transactional
    @POST
    @Path("/playable")
    public Response createPlayable(@Valid @NotNull CreatePlayableCharacterRequestDto request) {
        var created = createPlayableCharacterUseCase.execute(new CreatePlayableCharacterCommand(
                request.name(), request.description(), request.campaignId(),
                request.playerId(), request.characterClassId()));
        return Response.status(Response.Status.CREATED).entity(CharacterResponseDto.from(created)).build();
    }

    @GET
    public List<CharacterResponseDto> listByCampaign(@QueryParam("campaignId") UUID campaignId) {
        if (campaignId == null) {
            throw new BadRequestException("campaignId query parameter is required");
        }
        return listCharactersByCampaignUseCase.execute(campaignId).stream()
                .map(CharacterResponseDto::from)
                .toList();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") UUID id) {
        return findCharacterByIdUseCase.execute(id)
                .map(character -> Response.ok(CharacterResponseDto.from(character)).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @Transactional
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") UUID id, @Valid @NotNull UpdateCharacterRequestDto request) {
        return updateCharacterUseCase.execute(id, request.name(), request.description())
                .map(character -> Response.ok(CharacterResponseDto.from(character)).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }
}
