package ch.bzz.handy.service;

import ch.bzz.handy.data.DataHandler;
import ch.bzz.handy.model.Handymodell;
import jdk.nashorn.internal.objects.annotations.Getter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 services for reading, adding, changing and deleting handymodells
 */
@Path("handymodell")
public class HandymodellService {
    /**
     * read a list of all handymodells
     * @param sort
     * @return handymodells as JSON
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
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readHandymodell(
            @QueryParam("id") String handymodellID
    ){
        Handymodell handymodell = DataHandler.readHandymodellByID(handymodellID);
        return Response
                .status(200)
                .entity(handymodell)
                .build();
    }
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertHandymodell(
            @FormParam("name") String name,
            @FormParam("erscheinungsjahr") String erscheinungsjahr,
            @FormParam("akkulaufzeit") double akkulaufzeit,
            @FormParam("handymarkeID") String handymarkeID
    ){
        Handymodell handymodell = new Handymodell();
        handymodell.setHandymodellName(name);
        //handymodell.setHandymodellID(.toString());
        handymodell.setErscheinungsjahr(erscheinungsjahr);
        handymodell.setAkkulaufzeit(akkulaufzeit);
        handymodell.setHandymarkeID(handymarkeID);

        DataHandler.insertHandymodell(handymodell);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateHandymodell(
            @FormParam("handymodellID") String handymodellID,
            @FormParam("handymodellName") String handymodellName,
            @FormParam("erscheinungsjahr") String erscheinungsjahr,
            @FormParam("handymarkeID") String handymarkeID,
            @FormParam("akkulaufzeit") double akkulaufzeit

    ){
        int httpStatus = 200;
        Handymodell handymodell = DataHandler.readHandymodellByID(handymodellID);
        if (handymodell != null){
            handymodell.setHandymodellName(handymodellName);
            handymodell.setErscheinungsjahr(erscheinungsjahr);
            handymodell.setHandymarkeID(handymarkeID);
            handymodell.setAkkulaufzeit(akkulaufzeit);


            DataHandler.updateHandymodell();
        } else {
            httpStatus = 410;
        } return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
    /**
     * deletes handymodell
     * @param handymodellID
     * @return
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteHandymodell(
            @QueryParam("id") String handymodellID
    ){
        int httpStatus = 200;
        if (!DataHandler.deleteHandymodell(handymodellID)){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}