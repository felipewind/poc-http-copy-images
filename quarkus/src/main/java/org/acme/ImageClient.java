package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

public interface ImageClient {

    @GET
    @Produces("image/*")
    public Response getCopy();

}
