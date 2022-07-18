package ru.cbr.project.commons;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import ru.cbr.project.core.ResponseStatus;
import ru.cbr.project.view.ResponseBase;

/**
 *
 * @author Azat Safargalin
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<WebApplicationException> {

    private HttpServletRequest httpServletRequest;

    @Autowired
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public Response toResponse(final WebApplicationException ex) {
        Integer status = ex.getResponse().getStatus();
        String outMess = ex.getMessage();
        if (outMess == null || outMess.isEmpty()) {
            outMess = ex.toString();
        }
        String contentType = httpServletRequest.getHeader("Accept");
        return Response
                .status(status)
                .entity(new ResponseBase(status == 200 ? ResponseStatus.SUCCESS : ResponseStatus.FAIL, outMess))
                .header("Content-Type", contentType == null ? MediaType.APPLICATION_JSON : contentType)
                .build();
    }
}
