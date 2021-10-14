package io.github.dougllasfps.quarkussocial.rest;

import io.github.dougllasfps.quarkussocial.rest.dto.CreateUserRequest;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @POST
    public Response createUser( CreateUserRequest userRequest ){
        return Response.ok(userRequest).build();
    }

    @GET
    public Response listAllUsers(){
        return Response.ok().build();
    }

}
