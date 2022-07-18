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
@Path("validate")
public interface ValidateResource {

    /**
     * Валидация XML файла на основе связи с XSD
     * @param xmlEntity
     * @return
     */
    @POST
    @Valid
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ResponseView validateXml(@Valid ru.cbr.project.command.XmlEntity xmlEntity);
}
