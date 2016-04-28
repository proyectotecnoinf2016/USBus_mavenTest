package com.tecnoinf.proy.services;

import com.tecnoinf.proy.logic.auth.Authenticator;
import com.tecnoinf.proy.logic.auth.Credentials;
import com.tecnoinf.proy.logic.auth.intf.HTTPHeaderNames;
import com.tecnoinf.proy.services.intf.IAuthentication;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.security.auth.login.LoginException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.GeneralSecurityException;

/**
 * Created by JuanPablo on 4/28/2016.
 */
@Stateless
public class Authentication implements IAuthentication {

    @Override
    public Response login(@Context HttpHeaders httpHeaders,long tenantId, Credentials credentials) {
        Authenticator authenticator = Authenticator.getInstance();
        String authToken = httpHeaders.getHeaderString(HTTPHeaderNames.AUTH_TOKEN);
        try{
            authToken = authenticator.login(tenantId,credentials.getUsername(),credentials.getPassword());
            JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
            jsonObjBuilder.add( "auth_token", authToken );
            JsonObject jsonObj = jsonObjBuilder.build();


            return Response.status(Response.Status.OK).header("auth_token",authToken).build();

        }catch (final LoginException ex){
            return Response.status(Response.Status.UNAUTHORIZED).header("message","Error al autenticar").build();
        }
    }

    @Override
    @POST
    @Path("logout")
    public Response logout(@Context HttpHeaders httpHeaders) {
        try {
            Authenticator demoAuthenticator = Authenticator.getInstance();
            String authToken = httpHeaders.getHeaderString( HTTPHeaderNames.AUTH_TOKEN );
            demoAuthenticator.logout(authToken);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch ( final GeneralSecurityException ex ) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}


