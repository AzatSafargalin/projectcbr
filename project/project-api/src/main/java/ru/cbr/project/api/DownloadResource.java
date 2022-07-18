package ru.cbr.project.api;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ru.cbr.project.view.ResponseView;

/**
 *
 * @author Azat Safargalin
 */
@Path("download")
public interface DownloadResource {

    /**
     * Получение файла по имени
     * @param filename
     * @return 
     */
    @GET
    @Valid
    @Path("/getFile")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getXmlFile(@Valid @NotBlank @QueryParam("fileName") String filename);

    /**
     * Получение статуса файла + ссылки для скачивания
     * @param filename
     * @return 
     */
    @GET
    @Valid
    @Path("/getFileLink")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ResponseView getFileLink(@Valid @NotBlank @QueryParam("fileName") String filename);
}
