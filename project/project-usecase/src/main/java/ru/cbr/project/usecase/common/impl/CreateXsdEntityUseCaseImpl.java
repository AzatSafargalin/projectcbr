package ru.cbr.project.usecase.common.impl;

import java.io.IOException;
import java.io.InputStream;
import javax.ws.rs.WebApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.cbr.project.model.XsdEntity;
import ru.cbr.project.service.XsdEntityService;
import ru.cbr.project.usecase.common.CreateXsdEntityUseCase;

@Slf4j
@Component
public class CreateXsdEntityUseCaseImpl implements CreateXsdEntityUseCase {

    @Value("${commonparams.xsdpath}")
    private String xsdFolder;

    private XsdEntityService xsdEntityService;

    @Autowired
    public void setXsdEntityService(XsdEntityService xsdEntityService) {
        this.xsdEntityService = xsdEntityService;
    }

    @Override
    public XsdEntity createXsdEntity(final String xsdName) {
        log.info("Начало проверки наличия файла перед созданием xsd файла: xsdName={}", xsdName);
        this.checkFileExists(xsdFolder, xsdName);
        log.info("Начало проверки наличия файла в базе перед созданием xsd файла: xsdName={}", xsdName);
        this.checkAlreadySavedInDatabase(xsdName);
        log.info("Проверка прошла успешно. Создание файла xsd: xsdName={}", xsdName);
        return xsdEntityService.save(new XsdEntity().withName(xsdName));
    }

    private void checkAlreadySavedInDatabase(final String xsdName) {
        if (xsdEntityService.findByName(xsdName).isPresent()) {
            log.error("Xml файл {} уже сохранен в базе", xsdName);
            throw new WebApplicationException(String.format("Xml файл %s уже сохранен в базе", xsdName), 450);
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
