package ch.bzz.handy.service;

import ch.bzz.handy.data.DataHandler;
import ch.bzz.handy.model.Handymarke;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.*;

/*
 services für lesen, ändern, hinzufügen und löschen von Handymarken
 */
@Path("handymarke")
public class HandymarkeService {
    /**
     * liest eine Liste von allen Handymarken
     * @param sort
     * @return handymarke als JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listHandymarke(
            @QueryParam("sort")boolean sort,
            @CookieParam("userRole") String userRole
    ){
        int httpStatus;
        List<Handymarke> handymarkeList = null;
        if (userRole == null || userRole.equals("guest")){
            httpStatus = 403;
        }else {
            handymarkeList = DataHandler.readAllHandymarkes();
            if (sort){
                Collections.sort(handymarkeList, new Comparator<Handymarke>() {
                    @Override
                    public int compare(Handymarke handymarke, Handymarke t1) {
                        return handymarke.getHandymarkeName().compareTo(t1.getHandymarkeName());
                    }
                });
            }
            httpStatus = 200;
        }
        return Response
                .status(httpStatus)
                .entity(handymarkeList)
                .build();
    }
    /**
     * liest eine Handymarke über der UUID
     * @param handymarkeUUID den Schlüssel
     * @return handymarke
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readHandymarke(
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @NotEmpty
            @QueryParam("uuid") String handymarkeUUID,
            @CookieParam("userRole") String userRole
    ){
        int httpStatus;
        Handymarke handymarke = null;
        if (userRole == null || userRole.equals("guest")){
            httpStatus = 403;
        }else {
            httpStatus = 200;
            handymarke = DataHandler.readHandymarkeByUUID(handymarkeUUID);
            if (handymarke == null){
                httpStatus = 410;
            }
        }

        return Response
                .status(httpStatus)
                .entity(handymarke)
                .build();

    }
    /**
     * fügt ein neues Handymodell hinzu
     * @param handymarke
     * @return Nachricht
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertHandymarke(
            @Valid @BeanParam Handymarke handymarke,
            @FormParam("gruendungsDatum")@NotEmpty @Pattern(regexp = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")
                    String gruendungsDatum,
            @CookieParam("userRole") String userRole


    ){
        int httpStatus;
        if (userRole == null || userRole.equals("guest") || userRole.equals("user")){
            httpStatus = 403;
        }else {
            httpStatus = 200;

            handymarke.setHandymarkeUUID(UUID.randomUUID().toString());
            handymarke.setGruendungsDatum(gruendungsDatum);
            DataHandler.insertHandymarke(handymarke);
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * ändert eine Handymarke
     * @param handymarke die uuid von der Handymarke
     * @return Nachricht
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateHandymarke(
            @Valid @BeanParam Handymarke handymarke,
            @FormParam("gruendungsDatum")@NotEmpty @Pattern(regexp = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")
                    String gruendungsDatum,
            @CookieParam("userRole")String userRole

    ){
        int httpStatus;
        Handymarke oldhandymarke = null;
        if (userRole == null || userRole.equals("guest") ||userRole.equals("user")){
            httpStatus = 403;
        }else {
            httpStatus = 200;
            oldhandymarke = DataHandler.readHandymarkeByUUID(handymarke.getHandymarkeUUID());
            if (oldhandymarke != null){
                oldhandymarke.setHandymarkeName(handymarke.getHandymarkeName());
                oldhandymarke.setHerkunftsland(handymarke.getHerkunftsland());
                oldhandymarke.setGruendungsDatum(gruendungsDatum);
                DataHandler.updateHandymarke();
            } else {
                httpStatus = 410;
            }
        }
 return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
    /**
     * löscht eine Handymarke
     * @param handymarkeUUID der Schlüssel
     * @return Nachricht
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteHandymarke(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String handymarkeUUID,
            @CookieParam("userRole")String userRole
    ){
        int httpStatus;
        if (userRole == null || userRole.equals("guest")||userRole.equals("user")){
            httpStatus = 403;
        }else {
            httpStatus = 200;
            if (!DataHandler.deleteHandymarke(handymarkeUUID)){
                httpStatus = 410;
            }
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}