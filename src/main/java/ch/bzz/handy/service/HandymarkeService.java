package ch.bzz.handy.service;

import ch.bzz.handy.data.DataHandler;
import ch.bzz.handy.model.Handymarke;
import jdk.nashorn.internal.objects.annotations.Getter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        List<Handymarke> handymarkeList = DataHandler.getInstance().readAllHandymarke();
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
            @QueryParam("id") String handymarkeID
    ){
        Handymarke handymarke = DataHandler.getInstance().readHandymarkeByID(handymarkeID);
        return Response
                .status(200)
                .entity(handymarke)
                .build();
    }

}