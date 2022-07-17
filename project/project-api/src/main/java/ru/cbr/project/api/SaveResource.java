package ru.cbr.project.api;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ru.cbr.project.view.ResponseView;

/**
 *
 * @author Azat Safargalin
 */
@Path("save")
public interface SaveResource {

    @POST
    @Valid
    @Path("/xml")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ResponseView saveXml(@Valid ru.cbr.project.command.XmlEntity xmlEntity);

    @POST
    @Valid
    @Path("/xmlXsdBound")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ResponseView saveXmlXsdBound(@Valid ru.cbr.project.command.XmlXsdPair xmlXsdPair);

}
