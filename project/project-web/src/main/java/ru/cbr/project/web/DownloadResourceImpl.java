package ru.cbr.project.web;

import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.cbr.project.api.DownloadResource;
import ru.cbr.project.core.FileStatus;
import ru.cbr.project.usecase.ReceiveXmlFile;
import ru.cbr.project.view.ResponseView;

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
                .header("Content-Type", "application/xml")
                .header("Content-Disposition", "attachment; filename=" + filename)
                .status(200)
                .build();
    }

    @Override
    public ResponseView getFileLink(final String filename) {
        FileStatus fs = receiveXmlFile.getFileStatus(filename);
        return FileStatus.UNKNOWN.equals(fs)
                ? new ResponseView(454, "Неизвестный файл", null)
                : new ResponseView(200, "Статус файла: " + fs,
                        "http://localhost:8080/project-web/test/getFile?fileName=" + filename);
    }

}
