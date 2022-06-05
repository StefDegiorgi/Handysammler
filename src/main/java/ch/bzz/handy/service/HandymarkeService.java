package ch.bzz.handy.service;

import ch.bzz.handy.data.DataHandler;
import ch.bzz.handy.model.Handymarke;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.*;

/*
 services for reading, adding, changing and deleting handymarke
 */
@Path("handymarke")
public class HandymarkeService {
    /**
     * read a list of all handymarke
     * @param sort
     * @return handymarke as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listHandymarke(@QueryParam("sort")boolean sort){
        List<Handymarke> handymarkeList = DataHandler.readAllHandymarkes();
        if (sort){
            Collections.sort(handymarkeList, new Comparator<Handymarke>() {
                @Override
                public int compare(Handymarke handymarke, Handymarke t1) {
                    return handymarke.getHandymarkeName().compareTo(t1.getHandymarkeName());
                }
            });
        }
        return Response
                .status(200)
                .entity(handymarkeList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readHandymarke(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String handymarkeUUID
    ){
        int httpStatus = 200;
        Handymarke handymarke = DataHandler.readHandymarkeByUUID(handymarkeUUID);
        if (handymarke == null){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(handymarke)
                .build();

    }

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertHandymarke(
            @FormParam("name") String name,
            @FormParam("herkunftsland") String herkunftsland,
            @FormParam("gruendungsDatum") String gruendungsDatum
    ){
        Handymarke handymarke = new Handymarke();
        handymarke.setHandymarkeName(name);
        handymarke.setHandymarkeUUID(UUID.randomUUID().toString());
        handymarke.setHerkunftsland(herkunftsland);
        handymarke.setGruendungsDatum(LocalDate.parse(gruendungsDatum));

        DataHandler.insertHandymarke(handymarke);
        return Response
                .status(200)
                .entity("")
                .build();
    }


    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateHandymarke(
            @FormParam("handymarkeUUID") String handymarkeUUID,
            @FormParam("handymarkeName") String handymarkeName,
            @FormParam("herkunftsland") String herkunftsland,
            @FormParam("gruendungsDatum") String gruendungsDatum

    ){
        int httpStatus = 200;
        Handymarke handymarke = DataHandler.readHandymarkeByUUID(handymarkeUUID);
        if (handymarke != null){
            handymarke.setHandymarkeName(handymarkeName);
            handymarke.setHerkunftsland(herkunftsland);
            handymarke.setGruendungsDatum(LocalDate.parse(gruendungsDatum));


            DataHandler.updateHandymarke();
        } else {
            httpStatus = 410;
        } return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteHandymarke(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String handymarkeUUID
    ){
        int httpStatus = 200;
        if (!DataHandler.deleteHandymarke(handymarkeUUID)){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}