package org.acme;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.IOUtils;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
@Path("/image")
@Tag(name = "Image copy")
public class ImageResource {

        private static final Logger LOG = LoggerFactory.getLogger(ImageResource.class.getName());

        @Path("copy/{imageURL}")
        @GET
        @Operation(summary = "Copy one image and return the same Response")
        public Response getCopy(@PathParam(value = "imageURL") String imageURL) {

                LOG.debug("getCopy() url=[" + imageURL + "]");

                Response clientResponse = getImageClient(imageURL).getCopy();

                LOG.debug("getCopy() url=[" + imageURL + "]" + clientResponse.getHeaderString("content-type"));

                return clientResponse;
        }

        @Path("base64/{imageURL}")
        @GET
        @Operation(summary = "Copy one image and return it as Base64")
        public Response getBase64(@PathParam(value = "imageURL") String imageURL) throws IOException {

                LOG.debug("getBase64() url=[" + imageURL + "]");

                Response clientResponse = getImageClient(imageURL).getCopy();

                LOG.debug("getBase64() url=[" + imageURL + "]" + clientResponse.getHeaderString("content-type"));

                InputStream is = clientResponse.readEntity(InputStream.class);

                byte[] bytes = IOUtils.toByteArray(is);

                String encodedImage = Base64.getEncoder().encodeToString(bytes);

                EncodedImageDto encodedImageDto = new EncodedImageDto(clientResponse.getHeaderString("content-type"),
                                encodedImage);

                ObjectMapper mapper = new ObjectMapper();

                String jsonString = mapper.writeValueAsString(encodedImageDto);

                LOG.debug("Response do client=[" + jsonString + "]");

                Response response = Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON)
                                .entity(encodedImageDto).build();

                return response;
        }

        private ImageClient getImageClient(String imageURL) {

                URI uri = URI.create(imageURL);

                ImageClient imageClient = RestClientBuilder.newBuilder().baseUri(uri).readTimeout(3l, TimeUnit.SECONDS)
                                .build(ImageClient.class);

                return imageClient;

        }

}
