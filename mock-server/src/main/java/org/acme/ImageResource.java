package org.acme;

import java.io.InputStream;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RequestScoped
@Path("/image")
@Tag(name = "Mocked images")
public class ImageResource {

        private static final Logger LOG = LoggerFactory.getLogger(ImageResource.class.getName());

        @Path("earth.png")
        @GET
        public Response getEarth() {
    
            LOG.debug("getEarth()");
    
            InputStream is = getClass().getClassLoader().getResourceAsStream("images/earth.png");
    
            return Response.status(Response.Status.OK).type("image/png").entity(is).build();
        }
    
        @Path("moon.jpg")
        @GET
        public Response getMoon() {
    
            LOG.debug("getMoon()");
    
            InputStream is = getClass().getClassLoader().getResourceAsStream("images/moon.jpg");
    
            return Response.status(Response.Status.OK).type("image/jpg").entity(is).build();
        }
    
        @Path("solarsystem.svg")
        @GET
        public Response getSolarSystem() {
    
            LOG.debug("getSolarSystem()");
    
            InputStream is = getClass().getClassLoader().getResourceAsStream("images/solarsystem.svg");
    
            return Response.status(Response.Status.OK).type("image/svg+xml").entity(is).build();
        }

}
