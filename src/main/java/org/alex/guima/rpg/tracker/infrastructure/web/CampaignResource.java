package org.alex.guima.rpg.tracker.infrastructure.web;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.alex.guima.rpg.tracker.domain.model.Campaign;
import org.alex.guima.rpg.tracker.infrastructure.web.dto.CampaignResponseDto;
import org.alex.guima.rpg.tracker.infrastructure.web.dto.CreateCampaignRequestDto;
import org.alex.guima.rpg.tracker.usecase.CreateCampaignUseCase;

@Path("/campaign")
public class CampaignResource {

    private final CreateCampaignUseCase createCampaignUseCase;

    @Inject
    public CampaignResource(CreateCampaignUseCase createCampaignUseCase) {
        this.createCampaignUseCase = createCampaignUseCase;
    }

    @Transactional
    @POST
    public Response create(CreateCampaignRequestDto request) {
        Campaign campaign = new Campaign(request.title(), request.description());

        var created =  createCampaignUseCase.execute(campaign);
        var response = new CampaignResponseDto(created.title(), created.description());

        return Response.status(Response.Status.CREATED).entity(response).build();
    }
}
