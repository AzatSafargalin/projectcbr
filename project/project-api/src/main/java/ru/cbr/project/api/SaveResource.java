package ru.cbr.project.api;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Azat Safargalin
 */
@Path("save")
public interface SaveResource {

    /**
     * Сохранение XML файла в таблицу XML_PROCEED
     *
     * @param xmlEntity
     * @return
     */
    @POST
    @Valid
    @Path("/xml")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ru.cbr.project.view.SaveXmlResponse saveXml(@Valid ru.cbr.project.command.XmlEntity xmlEntity);

    /**
     * Создание связи XML <-> XSD
     *
     * @param xmlXsdPair
     * @return
     */
    @POST
    @Valid
    @Path("/xmlXsdBound")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ru.cbr.project.view.SaveXmlXsdBoundResponse saveXmlXsdBound(@Valid ru.cbr.project.command.XmlXsdPair xmlXsdPair);

}
