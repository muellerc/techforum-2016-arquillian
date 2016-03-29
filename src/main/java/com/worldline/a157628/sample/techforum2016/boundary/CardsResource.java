package com.worldline.a157628.sample.techforum2016.boundary;

import java.util.List;

import com.worldline.a157628.sample.techforum2016.entity.Card;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("cards")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class CardsResource {

    @Inject
    CardsRepository repository;

    @Context
    private UriInfo uriInfo;

    @GET
    public Response getAll() {
        List<Card> cards = repository.getAll();

        if (cards.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(cards).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") String id) {
        Card card = repository.get(id);

        if (card == null) {
            return Response.noContent().build();
        }

        return Response.ok(card).build();
    }

    @POST
    public Response create(Card card) {
        try {
            card = repository.create(card);

            return Response.created(uriInfo.getAbsolutePathBuilder().path(card.getId()).build()).build();
        } catch (IllegalArgumentException iae) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") String id, Card card) {
        try {
            card.setId(id);
            card = repository.update(card);

            return Response.accepted().build();
        } catch (IllegalArgumentException iae) {
            return Response.noContent().build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id) {
        try {
            repository.delete(id);
            return Response.accepted().build();
        } catch (IllegalArgumentException iae) {
            return Response.noContent().build();
        }
    }
}