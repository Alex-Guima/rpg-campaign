package org.alex.guima.rpg.tracker.infrastructure.web;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.alex.guima.rpg.tracker.domain.model.Campaign;
import org.alex.guima.rpg.tracker.infrastructure.web.dto.CampaignResponseDto;
import org.alex.guima.rpg.tracker.infrastructure.web.dto.CreateCampaignRequestDto;
import org.alex.guima.rpg.tracker.infrastructure.web.dto.UpdateCampaignRequestDto;
import org.alex.guima.rpg.tracker.usecase.CreateCampaignUseCase;
import org.alex.guima.rpg.tracker.usecase.FindCampaignByIdUseCase;
import org.alex.guima.rpg.tracker.usecase.UpdateCampaignUseCase;

import java.util.UUID;

@Path("/campaign")
public class CampaignResource {

    private final CreateCampaignUseCase createCampaignUseCase;
    private final FindCampaignByIdUseCase findCampaignByIdUseCase;
    private final UpdateCampaignUseCase updateCampaignUseCase;

    @Inject
    public CampaignResource(
            CreateCampaignUseCase createCampaignUseCase,
            FindCampaignByIdUseCase findCampaignByIdUseCase,
            UpdateCampaignUseCase updateCampaignUseCase) {
        this.createCampaignUseCase = createCampaignUseCase;
        this.findCampaignByIdUseCase = findCampaignByIdUseCase;
        this.updateCampaignUseCase = updateCampaignUseCase;
    }

    @Transactional
    @POST
    public Response create(CreateCampaignRequestDto request) {
        Campaign campaign = new Campaign(request.title(), request.description());

        var created = createCampaignUseCase.execute(campaign);
        var response = new CampaignResponseDto(created.id(), created.title(), created.description());

        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") UUID id) {
        return findCampaignByIdUseCase.execute(id)
                .map(campaign -> new CampaignResponseDto(campaign.id(), campaign.title(), campaign.description()))
                .map(dto -> Response.ok(dto).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @Transactional
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") UUID id, UpdateCampaignRequestDto request) {
        Campaign updatedCampaign = new Campaign(request.title(), request.description());
        return updateCampaignUseCase.execute(id, updatedCampaign)
                .map(campaign -> new CampaignResponseDto(campaign.id(), campaign.title(), campaign.description()))
                .map(dto -> Response.ok(dto).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }
}
