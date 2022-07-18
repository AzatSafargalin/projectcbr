package ru.cbr.project.web;

import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.cbr.project.api.DownloadResource;
import ru.cbr.project.core.FileStatus;
import ru.cbr.project.core.ResponseStatus;
import ru.cbr.project.usecase.ReceiveXmlFile;

@Slf4j
@Path("download")
public class DownloadResourceImpl implements DownloadResource {

    private ReceiveXmlFile receiveXmlFile;

    @Autowired
    public void setReceiveXmlFile(ReceiveXmlFile receiveXmlFile) {
        this.receiveXmlFile = receiveXmlFile;
    }

    @Override
    public Response getXmlFile(final String filename) {
        if (FileStatus.UNKNOWN.equals(receiveXmlFile.getFileStatus(filename))) {
            log.error("Попытка получения неизвестного файла.");
            throw new WebApplicationException("Неизвестный файл", 454);
        }

        byte[] byteArr = receiveXmlFile.getFile(filename);
        return Response
                .ok(byteArr)
                .header("Content-Type", MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=" + filename)
                .status(200)
                .build();
    }

    @Override
    public ru.cbr.project.view.DownloadResponse getFileLink(final String filename) {
        FileStatus fs = receiveXmlFile.getFileStatus(filename);
        return FileStatus.UNKNOWN.equals(fs)
                ? new ru.cbr.project.view.DownloadResponse(ResponseStatus.FAIL, "Неизвестный файл", null)
                : new ru.cbr.project.view.DownloadResponse(ResponseStatus.SUCCESS, "Статус файла: " + fs,
                        // Не успел поправить =\
                        "http://localhost:8080/project-web/test/getFile?fileName=" + filename);
    }

}
