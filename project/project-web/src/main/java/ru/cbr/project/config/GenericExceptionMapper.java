package ru.cbr.project.config;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Azat Safargalin
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<WebApplicationException> {

    @Override
    public Response toResponse(final WebApplicationException ex) {
        Integer status = ex.getResponse().getStatus();
        String outMess = ex.getMessage();
        if (outMess == null || outMess.isEmpty()) {
            outMess = ex.toString();
        }
        return Response
                .status(status)
                .entity(String.format("{\"status\": %d, \"message\": \"%s\"}", status, outMess))
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .build();
    }
}
