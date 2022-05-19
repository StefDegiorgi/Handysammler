package ch.bzz.handy.service;

import ch.bzz.handy.data.DataHandler;
import ch.bzz.handy.model.Handymodell;
import jdk.nashorn.internal.objects.annotations.Getter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/*
 services for reading, adding, changing and deleting handymodells
 */
@Path("handymodell")
public class HandymodellService {
    /**
     * read a list of all handymodells
     * @return handymodells as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listHandymodell(){
        List<Handymodell> handymodellList = DataHandler.getInstance().readAllHandymodells();
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
        Handymodell handymodell = DataHandler.getInstance().readHandymodellByID(handymodellID);
        return Response
                .status(200)
                .entity(handymodell)
                .build();
    }

}