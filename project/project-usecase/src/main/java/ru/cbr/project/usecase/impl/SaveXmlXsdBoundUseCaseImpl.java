package ru.cbr.project.usecase.impl;

import java.util.Optional;
import javax.ws.rs.WebApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cbr.project.model.XmlEntity;
import ru.cbr.project.model.XmlXsdBound;
import ru.cbr.project.model.XsdEntity;
import ru.cbr.project.service.XmlEntityService;
import ru.cbr.project.service.XmlXsdBoundService;
import ru.cbr.project.service.XsdEntityService;
import ru.cbr.project.usecase.SaveXmlXsdBoundUseCase;
import ru.cbr.project.usecase.common.CreateXmlEntityUseCase;
import ru.cbr.project.usecase.common.CreateXsdEntityUseCase;

@Slf4j
@Component
public class SaveXmlXsdBoundUseCaseImpl implements SaveXmlXsdBoundUseCase {

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

    private XsdEntityService xsdEntityService;

    @Autowired
    public void setXsdEntityService(XsdEntityService xsdEntityService) {
        this.xsdEntityService = xsdEntityService;
    }

    private CreateXsdEntityUseCase createXsdEntityUseCase;

    @Autowired
    public void setCreateXsdEntityUseCase(CreateXsdEntityUseCase createXsdEntityUseCase) {
        this.createXsdEntityUseCase = createXsdEntityUseCase;
    }

    private XmlXsdBoundService xmlXsdBoundService;

    @Autowired
    public void setXmlXsdBoundService(XmlXsdBoundService xmlXsdBoundService) {
        this.xmlXsdBoundService = xmlXsdBoundService;
    }

    @Override
    public XmlXsdBound saveXsd(final String xmlName, final String xsdName) {

        log.info("Начало работы сервиса saveXsd: xml={}, xsd={}", xmlName, xsdName);

        if (Strings.isBlank(xmlName) || Strings.isBlank(xsdName)) {
            log.error("Передано пустое имя файла");
            throw new WebApplicationException("Название файла не может быть пустым");
        }

        Optional<XmlEntity> xmlEntityOpt = xmlEntityService.findByName(xmlName);
        XmlEntity xmlEntity = xmlEntityOpt.isPresent() ? xmlEntityOpt.get() : createXmlEntityUseCase.createXmlEntity(xmlName);

        Optional<XsdEntity> xsdEntityOpt = xsdEntityService.findByName(xsdName);
        XsdEntity xsdEntity = xsdEntityOpt.isPresent() ? xsdEntityOpt.get() : createXsdEntityUseCase.createXsdEntity(xsdName);

        Optional<XmlXsdBound> xmlXsdBoundOpt = xmlXsdBoundService.findByXmlEntity(xmlEntity);

        if (xmlXsdBoundOpt.isPresent()) {
            log.error("Связь для xml документа уже существует: xml={}", xmlName);
            throw new WebApplicationException(String.format("Связь для xml документа уже существует: xml=%s", xmlName), 450);
        }
        log.info("Сохраняем связь документов xml и xsd в таблицу связей: xml={}, xsd={}", xmlEntity, xsdEntity);

        return xmlXsdBoundService.save(new XmlXsdBound()
                .withXmlEntity(xmlEntity)
                .withXsdEntity(xsdEntity));
    }

}
