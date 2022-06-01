package ch.bzz.handy.service;

import ch.bzz.handy.data.DataHandler;
import ch.bzz.handy.model.Handymodell;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

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
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertHandymodell(
            @FormParam("name") String name,
            @FormParam("akkulaufzeit") double akkulaufzeit,
            @FormParam("handymarkeUUID") String handymarkeUUID
    ){
        Handymodell handymodell = new Handymodell();
        handymodell.setHandymodellName(name);
        handymodell.setHandymodellUUID(UUID.randomUUID().toString());
        handymodell.setAkkulaufzeit(akkulaufzeit);
        handymodell.setHandymarkeUUID(handymarkeUUID);

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
            @FormParam("handymodellUUID") String handymodellUUID,
            @FormParam("handymodellName") String handymodellName,
            @FormParam("akkulaufzeit") double akkulaufzeit,
            @FormParam("handymarkeUUID") String handymarkeUUID


            ){
        int httpStatus = 200;
        Handymodell handymodell = DataHandler.readHandymodellByUUID(handymodellUUID);
        if (handymodell != null){
            handymodell.setHandymodellName(handymodellName);
            handymodell.setAkkulaufzeit(akkulaufzeit);
            handymodell.setHandymarkeUUID(handymarkeUUID);



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
     * @param handymodellUUID
     * @return
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteHandymodell(
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