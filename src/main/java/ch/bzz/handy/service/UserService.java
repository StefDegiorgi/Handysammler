package ch.bzz.handy.service;


import ch.bzz.handy.data.UserData;
import ch.bzz.handy.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.status;

/**
 * services für authentifikation und authorizarion von Benutzer
 */
@Path("user")
public class UserService {

    @POST
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login (
            @FormParam("username") String username,
            @FormParam("password") String password
    ){
        int httpStatus;

        User user = UserData.findUser(username, password);
        if (user == null || user.getRole() == null || user.getRole().equals("guest")){
            httpStatus = 404;
        } else {
            httpStatus = 200;
        }
        NewCookie cookie = new NewCookie(
                "userRole",
                user.getRole(),
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        Response response = status(httpStatus)
                .entity("")
                .cookie(cookie)
                .build();
        return response;
    }

    @DELETE
    @Path("logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout(){
       NewCookie cookie = new NewCookie(
                "userRole",
                "guest",
                "/",
                "",
                "Logout-Cookie",
                1,
                false
        );
        return Response
                .status(200)
                .entity("")
                .cookie(cookie)
                .build();
    }


}
