package com.example;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Path("/rack")
public class RackResource {

    @Inject
    EntityManager entityManager;
    public static List<Rack> rackList = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRack() {
        List<Rack> rackList1 =  entityManager.createQuery("Select r FROM Rack r", Rack.class).getResultList();
        return Response.ok(rackList).build();
    }

    @GET
    @Path("/id")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getId() {

        return Response.ok("ID generation").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createRack(Rack newRack) {
        entityManager.persist(newRack);
        return Response.ok(newRack).build();
    }

    @PUT
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response rackUpdate(
            @PathParam("id") UUID id,
            Rack updatedRack) {

        Rack existingRack = findRackById(id);

        if (existingRack != null) {

            existingRack.setName(updatedRack.getName());

            existingRack.setDate(updatedRack.getDate());
            entityManager.persist(existingRack);
            return Response.ok(existingRack).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteRack(@PathParam("id") UUID id) {
        Rack rackToDelete = findRackById(id);

        if (rackToDelete != null) {
            entityManager.remove(rackToDelete);
            return Response.ok("Rack deleted successfully").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    private Rack findRackById(UUID id) {
        return entityManager.find(Rack.class, id);
    }

}
