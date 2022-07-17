package ru.cbr.project.usecase.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import javax.ws.rs.WebApplicationException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;
import ru.cbr.project.model.XmlEntity;
import ru.cbr.project.model.XmlInvalid;
import ru.cbr.project.model.XmlValid;
import ru.cbr.project.model.XmlXsdBound;
import ru.cbr.project.service.XmlEntityService;
import ru.cbr.project.service.XmlInvalidService;
import ru.cbr.project.service.XmlValidService;
import ru.cbr.project.service.XmlXsdBoundService;
import ru.cbr.project.service.XsdEntityService;
import ru.cbr.project.usecase.ValidateXmlUseCase;
import ru.cbr.project.usecase.common.CreateXmlEntityUseCase;

@Slf4j
@Component
public class ValidateXmlUseCaseImpl implements ValidateXmlUseCase {

    @Value("${commonparams.xmlpath}")
    private String xmlFolder;

    @Value("${commonparams.xsdpath}")
    private String xsdFolder;

    private XmlXsdBoundService xmlXsdBoundService;

    @Autowired
    public void setXmlXsdBoundService(XmlXsdBoundService xmlXsdBoundService) {
        this.xmlXsdBoundService = xmlXsdBoundService;
    }

    private XmlEntityService xmlEntityService;

    @Autowired
    public void setXmlEntityService(XmlEntityService xmlEntityService) {
        this.xmlEntityService = xmlEntityService;
    }

    private XsdEntityService xsdEntityService;

    @Autowired
    public void setXsdEntityService(XsdEntityService xsdEntityService) {
        this.xsdEntityService = xsdEntityService;
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

    private CreateXmlEntityUseCase createXmlEntityUseCase;

    @Autowired
    public void setCreateXmlEntityUseCase(CreateXmlEntityUseCase createXmlEntityUseCase) {
        this.createXmlEntityUseCase = createXmlEntityUseCase;
    }

    /**
     * Сервис валидации xml документа по связи с xsd из базы данных
     *
     * @param xmlName - имя xml документа
     * @return XmlValid, если успешно валидирован throws
     * WebApplicationException, если ошибка валидации
     */
    @Override
    @Transactional
    public XmlValid validateXml(final String xmlName) {

        log.info("Начало работы сервиса validateXml: xml={}", xmlName);

        if (Strings.isBlank(xmlName)) {
            log.error("Передано пустое имя файла");
            throw new WebApplicationException("Название файла не может быть пустым");
        }

        // Поиск сущности xmlEntity
        Optional<XmlEntity> xmlEntityOpt = xmlEntityService.findByName(xmlName);
        XmlEntity xmlEntity = xmlEntityOpt.isPresent() ? xmlEntityOpt.get() : createXmlEntityUseCase.createXmlEntity(xmlName);

        // Проверка на валидность
        if (xmlValidService.findByXmlEntity(xmlEntity).isPresent()) {
            log.error("Документ уже валидирован: xml={}", xmlName);
            throw new WebApplicationException(String.format("Документ %s уже валидирован.", xmlName));
        }

        // Поиск связи для xmlEntity
        Optional<XmlXsdBound> xmlXsdBoundOpt = xmlXsdBoundService.findByXmlEntity(xmlEntity);

        if (!xmlXsdBoundOpt.isPresent()) {
            log.error("Не найден xsd документ для xml={}", xmlName);
            throw new WebApplicationException(String.format("Не найден xsd документ для %s", xmlName));
        }

        // Проверка валидности xsd документа для xml
        XmlXsdBound xmlXsdBound = xmlXsdBoundOpt.get();

        if (xmlXsdBound.getXsdEntity() == null || Strings.isBlank(xmlXsdBound.getXsdEntity().getName())) {
            log.error("Ошибка имени файла xsd: xml={}", xmlName);
            throw new WebApplicationException(String.format("Ошибка сохранения связи для %s. Обратитесь к администратору.", xmlName));
        }

        // Получение путей для xml и xsd файлов
        String xsdName = xmlXsdBound.getXsdEntity().getName();

        String xmlPath = new StringBuilder(xmlFolder).append("/").append(xmlEntity.getName()).toString();
        String xsdPath = new StringBuilder(xsdFolder).append("/").append(xsdName).toString();

        log.info("Начало валидации документа: xml={}, xsd={}", xmlPath, xsdPath);

        try (InputStream xml = getClass().getClassLoader().getResourceAsStream(xmlPath);
                InputStream xsd = getClass().getClassLoader().getResourceAsStream(xsdPath);) {
            if (xml == null || xsd == null) {
                log.error("Ошибка чтения документа: {}", xml == null ? xmlPath : xsdPath);
                throw new WebApplicationException("Ошибка чтения документа: " + xml == null ? xmlPath : xsdPath, 454);
            }

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));

            // Если успешно, то сохраняем в таблицу валидированных xml
            log.info("Сохраняем {} в таблицу валидных xml в связи с успешной валидацией");
            XmlValid xmlValid = xmlValidService.save(new XmlValid().withXmlEntity(xmlEntity));

            // И удаляем из таблицы невалидных
            if (xmlInvalidService.findByXmlEntity(xmlEntity).isPresent()) {
                log.info("Удаляем {} из таблицы невалидных xml в связи с успешной валидацией");
                xmlInvalidService.deleteByXmlEntity(xmlEntity);
            }

            log.info("Документ успешно валидирован: xml={}, xsd={}", xmlPath, xsdPath);
            return xmlValid;

        } catch (IOException | SAXException ex) {
            if (xmlValidService.findByXmlEntity(xmlEntity).isPresent()) {
                log.info("Удаляем {} из таблицы валидированных xml", xmlEntity);
                xmlValidService.deleteByXmlEntity(xmlEntity);
            }

            if (!xmlInvalidService.findByXmlEntity(xmlEntity).isPresent()) {
                log.info("Сохраняем документ {} в таблицу невалидных xml", xmlEntity);
                xmlInvalidService.save(new XmlInvalid()
                        .withXmlEntity(xmlEntity));
            } else {
                log.info("Не добавляем документ {} в таблицу невалидных xml, так как он уже там есть", xmlEntity);
            }

            if (ex instanceof IOException) {
                log.error("Ошибка чтения документа: xml={}, xsd={}\nmessage={}", xmlPath, xsdPath, ex.getMessage());
                throw new WebApplicationException(String.format("Ошибка чтения документа: xml=%s, xsd=%s",
                        xmlName, xsdName), 454);
            }
            log.error("Ошибка валидации документа: xml={}, xsd={}\nmessage={}", xmlPath, xsdPath, ex.getMessage());

            throw new WebApplicationException(String.format("Ошибка валидации документа: xml=%s, xsd=%s\nmessage=%s",
                    xmlName, xsdName, ex.getMessage()), 450);
        }

    }

}
