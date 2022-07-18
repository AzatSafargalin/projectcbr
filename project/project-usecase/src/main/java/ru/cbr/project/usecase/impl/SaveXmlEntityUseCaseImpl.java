package ru.cbr.project.usecase.impl;

import java.util.Optional;
import javax.ws.rs.WebApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.cbr.project.model.XmlEntity;
import ru.cbr.project.model.XmlInvalid;
import ru.cbr.project.model.XmlProceed;
import ru.cbr.project.model.XmlValid;
import ru.cbr.project.service.XmlEntityService;
import ru.cbr.project.service.XmlInvalidService;
import ru.cbr.project.service.XmlProceedService;
import ru.cbr.project.service.XmlValidService;
import ru.cbr.project.usecase.SaveXmlEntityUseCase;
import ru.cbr.project.usecase.common.CreateXmlEntityUseCase;

@Slf4j
@Component
public class SaveXmlEntityUseCaseImpl implements SaveXmlEntityUseCase {

    private XmlEntityService xmlEntityService;

    @Autowired
    public void setXmlEntityService(XmlEntityService xmlEntityService) {
        this.xmlEntityService = xmlEntityService;
    }

    private CreateXmlEntityUseCase createXmlEntityUseCase;

    @Autowired
    public void setCreateXmlEntityUseCase(CreateXmlEntityUseCase createXmlEntityUseCase) {
        this.createXmlEntityUseCase = createXmlEntityUseCase;
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

    private XmlProceedService xmlProceedService;

    @Autowired
    public void setXmlProceedService(XmlProceedService xmlProceedService) {
        this.xmlProceedService = xmlProceedService;
    }

    @Override
    @Transactional
    public XmlProceed saveXml(final String xmlName) {
        
        log.info("Начало работы сервиса saveXml: xmlName={}", xmlName);
        if (Strings.isBlank(xmlName)) {
            log.error("Передано пустое имя файла");
            throw new WebApplicationException("Название файла не может быть пустым");
        }

        log.info("Поиск xml документа по имени в базе проекта: xmlName={}", xmlName);
        Optional<XmlEntity> xmlEntityOpt = xmlEntityService.findByName(xmlName);
        if (!xmlEntityOpt.isPresent()) {
            log.error("Файл {} не найден в локальной базе", xmlName);
            throw new WebApplicationException(String.format("Файл %s не найден в базе прооекта", xmlName), 454);
        }
        XmlEntity xmlEntity = xmlEntityOpt.isPresent() ? xmlEntityOpt.get() : createXmlEntityUseCase.createXmlEntity(xmlName);

        Optional<XmlProceed> xmlProceedOpt = xmlProceedService.findByXmlEntity(xmlEntity);

        if (xmlProceedOpt.isPresent()) {
            log.error("Файл {} уже сохранен в локальной таблице", xmlName);
            throw new WebApplicationException(String.format("Файл %s уже сохранен в локальной таблице", xmlName), 450);
        }

        Optional<XmlInvalid> xmlInvalid = xmlInvalidService.findByXmlEntity(xmlEntity);
        if (xmlInvalid.isPresent()) {
            log.error("Файл {} помечен как невалидный", xmlName);
            throw new WebApplicationException(String.format("Файл %s помечен как невалидный", xmlName), 450);
        }

        Optional<XmlValid> xmlValidOpt = xmlValidService.findByXmlEntity(xmlEntity);

        if (xmlValidOpt.isPresent()) {
            XmlProceed xmlProceed = new XmlProceed(null, xmlEntity);
            xmlProceed = xmlProceedService.save(xmlProceed);
            log.info("Файл {} сохранен в таблицу proceed", xmlName);
            return xmlProceed;
        }

        log.error("Файл {} не найден ни в одной таблице", xmlName);
        throw new WebApplicationException(String.format("Файл %s не найден ни в одной таблице", xmlName), 450);
    }

}
