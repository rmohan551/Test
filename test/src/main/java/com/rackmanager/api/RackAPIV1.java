package com.rackmanager.api;

import com.rackmanager.api.dto.request.RackRequest;
import com.rackmanager.api.dto.response.RackResponse;
import com.rackmanager.domain.service.RackService;
import com.rackmanager.infrastructure.exception.RackNotFoundException;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

/**
 * RackAPIV1 class.
 */
@Path("/v1/rack")
public class RackAPIV1 {

    @Inject
    RackService rackService;

    /**
     * Retrieve all racks on the database.
     * @return a {@link com.rackmanager.api.dto.response.RackListResponse}
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRacks() {
        return Response.ok(rackService.findAll()).build();
    }

    /**
     * Get a rack from a given id.
     * @param id an {@link UUID}
     * @return a {@link RackResponse}
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getId(@PathParam("id") final UUID id) {
        Response response;
        try {
            response = Response.ok(rackService.findById(id)).build();
        } catch (final RackNotFoundException e) {
            response = Response.status(Response.Status.NOT_FOUND).entity("{\"error\": \"rack not found\"}").build();
        }
        return response;
    }

    /**
     * Creates a new Rack.
     * @param newRack {@link RackRequest}
     * @return {@link RackResponse}
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createRack(final RackRequest newRack) {
        RackResponse rackResponse = rackService.saveOne(newRack);
        return Response.ok(rackResponse).build();
    }

    /**
     * Updates a rack if it exists.
     * @param id {@link UUID}
     * @param rackRequest {@link RackRequest}
     * @return {@link RackResponse}
     */
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response rackUpdate(
            @PathParam("id") final UUID id,
            final RackRequest rackRequest) {
        Response response;

        try {
            response = Response.ok(rackService.updateRack(rackRequest)).build();
        } catch (final RackNotFoundException e) {
            response = Response.status(Response.Status.NOT_FOUND).entity("{\"error\": \"rack not found\"}").build();
        }

        return response;
    }

    /**
     * Deletes a rack if existent.
     * @param id {@link UUID}
     * @return {@link Response}
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteRack(@PathParam("id") final UUID id) {
        Response response;

        try {
            rackService.delete(id);
            response = Response.ok().build();
        } catch (final RackNotFoundException e) {
            response = Response.status(Response.Status.NOT_FOUND).entity("{\"error\": \"rack not found\"}").build();
        }

        return response;
    }
}
