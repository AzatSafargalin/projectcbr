package ru.cbr.project.usecase.common.impl;

import java.io.IOException;
import java.io.InputStream;
import javax.ws.rs.WebApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.cbr.project.model.XmlEntity;
import ru.cbr.project.service.XmlEntityService;
import ru.cbr.project.usecase.common.CreateXmlEntityUseCase;

@Slf4j
@Component
public class CreateXmlEntityUseCaseImpl implements CreateXmlEntityUseCase {

    @Value("${commonparams.xmlpath}")
    private String xmlFolder;

    private XmlEntityService xmlEntityService;

    @Autowired
    public void setXmlEntityService(XmlEntityService xmlEntityService) {
        this.xmlEntityService = xmlEntityService;
    }

    @Override
    public XmlEntity createXmlEntity(final String xmlName) {
        log.info("Начало проверки перед созданием xml файла: xmlName={}", xmlName);
        this.checkFileExists(xmlFolder, xmlName);
        log.info("Начало проверки наличия файла в базе перед созданием xml файла: xsdName={}", xmlName);
        this.checkAlreadySavedInDatabase(xmlName);
        log.info("Проверка прошла успешно. Создание файла xml: xmlName={}", xmlName);
        return xmlEntityService.save(new XmlEntity().withName(xmlName));
    }

    private void checkAlreadySavedInDatabase(final String xmlName) {
        if (xmlEntityService.findByName(xmlName).isPresent()) {
            log.error("Xsd файл {} уже сохранен в базе", xmlName);
            throw new WebApplicationException(String.format("Xsd файл %s уже сохранен в базе", xmlName), 450);
        }
    }

    private void checkFileExists(String pathFolder, String fileName) {
        String str = new StringBuilder(pathFolder).append("/").append(fileName).toString();
        try (InputStream xml = getClass().getClassLoader().getResourceAsStream(str);) {
            if (xml == null) {
                log.error("Файл {} не найден", fileName);
                throw new WebApplicationException(String.format("Файл %s не найден", fileName), 454);
            }
        } catch (IOException ex) {
            log.error("Ошибка чтения файла {}", fileName);
            throw new WebApplicationException(String.format("Ошибка чтения файла %s", fileName), 454);
        }
    }

}
