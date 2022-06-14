package ch.bzz.handy.service;

import ch.bzz.handy.data.DataHandler;
import ch.bzz.handy.model.Handymodell;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/*
 services für lesen, ändern, hinzufügen und löschen von Handymodells
 */
@Path("handymodell")
public class HandymodellService {
    /**
     * liest eine Liste von allen Handymodells
     * @param sort
     * @return handymodells als JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listHandymodell(@QueryParam("sort") boolean sort){
        List<Handymodell> handymodellList = DataHandler.readAllHandymodells();
        if (sort) {
            Collections.sort(handymodellList, new Comparator<Handymodell>() {
                @Override
                public int compare(Handymodell handymodell, Handymodell t1) {
                    return handymodell.getHandymodellName().compareTo(t1.getHandymodellName());
                }
            });
        }
        return Response
                .status(200)
                .entity(handymodellList)
                .build();
    }
    /**
     * liest ein Handymodell über der UUID
     * @param handymodellUUID den Schlüssel
     * @return handymodell
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readHandymodell(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String handymodellUUID
    ){
        int httpStatus = 200;
        Handymodell handymodell = DataHandler.readHandymodellByUUID(handymodellUUID);
        if (handymodell == null){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(handymodell)
                .build();
    }
    /**
     * fügt ein neues Handymodell hinzu
     * @param handymarkeUUID die uuid von der Handymarke
     * @return Nachricht
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertHandymodell(
            @Valid @BeanParam Handymodell handymodell,
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("handymarkeUUID") String handymarkeUUID
    ){
        handymodell.setHandymodellUUID(UUID.randomUUID().toString());
        handymodell.setHandymarkeUUID(handymarkeUUID);

        DataHandler.insertHandymodell(handymodell);
        return Response
                .status(200)
                .entity("")
                .build();
    }
    /**
     * ändert ein Handymodell
     * @param handymarkeUUID die uuid von der Handymarke
     * @return Nachricht
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateHandymodell(
            @Valid @BeanParam Handymodell handymodell,
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("handymarkeUUID") String handymarkeUUID

    ){
        int httpStatus = 200;
        Handymodell oldhandymodell = DataHandler.readHandymodellByUUID(handymodell.getHandymodellUUID());
        if (oldhandymodell != null){
            oldhandymodell.setHandymodellName(handymodell.getHandymodellName());
            oldhandymodell.setAkkulaufzeit(handymodell.getAkkulaufzeit());
            oldhandymodell.setSeriennummer(handymodell.getSeriennummer());
            oldhandymodell.setHandymarkeUUID(handymarkeUUID);



            DataHandler.updateHandymodell();
        } else {
            httpStatus = 410;
        } return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
    /**
     * löscht ein Handymodell
     * @param handymodellUUID der Schlüssel
     * @return Nachricht
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteHandymodell(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String handymodellUUID
    ){
        int httpStatus = 200;
        if (!DataHandler.deleteHandymodell(handymodellUUID)){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}