package com.tecnoinf.proy.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ejb.Stateless;

/**
 * Created by JuanPablo on 4/28/2016.
 */
@Stateless
@Path("/{tenantId}/test")
public class TestREST {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test(@PathParam("tenantId") String tenantId){
            return "PRUEBA CON TENANT " + tenantId;
    }
}


