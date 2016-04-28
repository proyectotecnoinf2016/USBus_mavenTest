package com.tecnoinf.proy.services.intf;

import com.tecnoinf.proy.logic.auth.Credentials;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by JuanPablo on 4/28/2016.
 */

@Local
@Path("{tenantId}")
public interface IAuthentication {
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON )
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(
            @Context HttpHeaders httpHeaders,
            @PathParam("tenantId") long tenantId,
            Credentials credentials);

    @POST
    @Path( "logout" )
    public Response logout(
            @Context HttpHeaders httpHeaders
    );
}
