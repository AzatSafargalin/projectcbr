package ru.cbr.project.usecase.impl;

import java.io.InputStream;
import java.util.Optional;
import javax.ws.rs.WebApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.cbr.project.core.FileStatus;
import ru.cbr.project.model.XmlEntity;
import ru.cbr.project.model.XmlInvalid;
import ru.cbr.project.model.XmlProceed;
import ru.cbr.project.model.XmlValid;
import ru.cbr.project.service.XmlEntityService;
import ru.cbr.project.service.XmlInvalidService;
import ru.cbr.project.service.XmlProceedService;
import ru.cbr.project.service.XmlValidService;
import ru.cbr.project.usecase.ReceiveXmlFile;

@Slf4j
@Component
public class ReceiveXmlFileImpl implements ReceiveXmlFile {

    @Value("${commonparams.xmlpath}")
    private String xmlFolder;

    private XmlEntityService xmlEntityService;

    @Autowired
    public void setXmlEntityService(XmlEntityService xmlEntityService) {
        this.xmlEntityService = xmlEntityService;
    }

    private XmlProceedService xmlProceedService;

    @Autowired
    public void setXmlProceedService(XmlProceedService xmlProceedService) {
        this.xmlProceedService = xmlProceedService;
    }

    private XmlValidService xmlValidService;

    @Autowired
    public void setXmlValidService(XmlValidService xmlValidService) {
        this.xmlValidService = xmlValidService;
    }

    private XmlInvalidService xmlInvalidService;

    @Autowired
    public void setXmlInvalidService(XmlInvalidService xmlInvalidService) {
        this.xmlInvalidService = xmlInvalidService;
    }

    private XmlProceedService xmlProceedService1;

    @Autowired
    public void setXmlProceedService1(XmlProceedService xmlProceedService1) {
        this.xmlProceedService1 = xmlProceedService1;
    }

    @Override
    public byte[] getFile(final String xmlName) {

        String xmlPath = new StringBuilder(xmlFolder).append("/").append(xmlName).toString();
        try (InputStream xml = getClass().getClassLoader().getResourceAsStream(xmlPath)) {
            return IOUtils.toByteArray(xml);
        } catch (Exception ex) {
            log.error("Ошибка конвертирования файла xml: xmlName={}", xmlName);
            throw new WebApplicationException("Ошибка конвертирования файла xml: xmlName=" + xmlName, 550);
        }
    }

    @Override
    public FileStatus getFileStatus(final String xmlName) {
        Optional<XmlEntity> xmlEntityOpt = xmlEntityService.findByName(xmlName);
        if (!xmlEntityOpt.isPresent()) {
            return FileStatus.UNKNOWN;
        }
        XmlEntity xmlEntity = xmlEntityOpt.get();

        Optional<XmlValid> xmlValid = xmlValidService.findByXmlEntity(xmlEntity);
        if (xmlValid.isPresent()) {
            return FileStatus.VALID;
        }

        Optional<XmlInvalid> xmlInvalid = xmlInvalidService.findByXmlEntity(xmlEntity);
        if (xmlInvalid.isPresent()) {
            return FileStatus.INVALID;
        }

        Optional<XmlProceed> xmlProceeded = xmlProceedService.findByXmlEntity(xmlEntity);
        if (xmlProceeded.isPresent()) {
            return FileStatus.PROCEEDED;
        }

        return FileStatus.UNKNOWN;

    }

}
