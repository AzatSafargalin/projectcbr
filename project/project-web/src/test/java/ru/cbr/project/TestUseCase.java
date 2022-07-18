package ru.cbr.project;

import java.util.Objects;
import java.util.Optional;
import javax.ws.rs.WebApplicationException;
import org.eclipse.persistence.jpa.jpql.Assert;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.cbr.project.model.XmlEntity;
import ru.cbr.project.model.XmlProceed;
import ru.cbr.project.model.XmlValid;
import ru.cbr.project.model.XmlXsdBound;
import ru.cbr.project.model.XsdEntity;
import ru.cbr.project.service.XmlEntityService;
import ru.cbr.project.service.XsdEntityService;
import ru.cbr.project.usecase.SaveXmlEntityUseCase;
import ru.cbr.project.usecase.SaveXmlXsdBoundUseCase;
import ru.cbr.project.usecase.ValidateXmlUseCase;
import ru.cbr.project.usecase.common.CreateXmlEntityUseCase;
import ru.cbr.project.usecase.common.CreateXsdEntityUseCase;

/**
 *
 * @author Azat Safargalin
 */
@SpringBootTest
@ActiveProfiles("test")
public class TestUseCase {

    private SaveXmlEntityUseCase saveXmlEntityUseCase;

    private SaveXmlXsdBoundUseCase saveXmlXsdBoundUseCase;

    private ValidateXmlUseCase validateXmlUseCase;

    private CreateXmlEntityUseCase createXmlEntityUseCase;

    private CreateXsdEntityUseCase createXsdEntityUseCase;

    private XmlEntityService xmlEntityService;

    private XsdEntityService xsdEntityService;

    @Autowired
    public void setSaveXmlEntityUseCase(SaveXmlEntityUseCase saveXmlEntityUseCase) {
        this.saveXmlEntityUseCase = saveXmlEntityUseCase;
    }

    @Autowired
    public void setSaveXmlXsdBoundUseCase(SaveXmlXsdBoundUseCase saveXmlXsdBoundUseCase) {
        this.saveXmlXsdBoundUseCase = saveXmlXsdBoundUseCase;
    }

    @Autowired
    public void setValidateXmlUseCase(ValidateXmlUseCase validateXmlUseCase) {
        this.validateXmlUseCase = validateXmlUseCase;
    }

    @Autowired
    public void setCreateXmlEntityUseCase(CreateXmlEntityUseCase createXmlEntityUseCase) {
        this.createXmlEntityUseCase = createXmlEntityUseCase;
    }

    @Autowired
    public void setCreateXsdEntityUseCase(CreateXsdEntityUseCase createXsdEntityUseCase) {
        this.createXsdEntityUseCase = createXsdEntityUseCase;
    }

    @Autowired
    public void setXmlEntityService(XmlEntityService xmlEntityService) {
        this.xmlEntityService = xmlEntityService;
    }

    @Autowired
    public void setXsdEntityService(XsdEntityService xsdEntityService) {
        this.xsdEntityService = xsdEntityService;
    }

    @Test
    @Transactional
    public void createXmlTest() {
        XmlEntity xmlEntity = createXmlEntityUseCase.createXmlEntity("user.xml");
        Assert.isTrue(Objects.equals("user.xml", xmlEntity.getName()), "XmlEntity.id is not valid");

        WebApplicationException thrownAlreadySavedInDatabase = assertThrows(
                WebApplicationException.class,
                () -> createXmlEntityUseCase.createXmlEntity("user.xml"),
                "Expected createXmlEntityUseCase.createXmlEntity() to throw an exception, but it didn't");
        Assert.isTrue(Objects.equals(thrownAlreadySavedInDatabase.getMessage(), "Xml файл user.xml уже сохранен в базе"),
                "Error executing createXmlEntityUseCase.createXmlEntity");

        WebApplicationException thrownFileNotFound = assertThrows(
                WebApplicationException.class,
                () -> createXmlEntityUseCase.createXmlEntity("unknown_file.xml"),
                "Expected createXmlEntityUseCase.createXmlEntity() to throw an exception, but it didn't");
        Assert.isTrue(Objects.equals(thrownFileNotFound.getMessage(), "Файл unknown_file.xml не найден"),
                "Error executing createXmlEntityUseCase.createXmlEntity");
    }

    @Test
    @Transactional
    public void createXsdTest() {
        XsdEntity xsdEntity = createXsdEntityUseCase.createXsdEntity("user.xsd");
        Assert.isTrue(Objects.equals("user.xsd", xsdEntity.getName()), "XsdEntity.id is not valid");

        WebApplicationException thrownAlreadySavedInDatabase = assertThrows(
                WebApplicationException.class,
                () -> createXsdEntityUseCase.createXsdEntity("user.xsd"),
                "Expected createXsdEntityUseCase.createXsdEntity() to throw an exception, but it didn't");
        Assert.isTrue(Objects.equals(thrownAlreadySavedInDatabase.getMessage(), "Xml файл user.xsd уже сохранен в базе"),
                "Error executing createXsdEntityUseCase.createXsdEntity");

        WebApplicationException thrownFileNotFound = assertThrows(
                WebApplicationException.class,
                () -> createXsdEntityUseCase.createXsdEntity("unknown_file.xsd"),
                "Expected createXsdEntityUseCase.createXsdEntity() to throw an exception, but it didn't");
        Assert.isTrue(Objects.equals(thrownFileNotFound.getMessage(), "Файл unknown_file.xsd не найден"),
                "Error executing createXsdEntityUseCase.createXsdEntity");
    }

    @Test
    @Transactional
    public void saveXmlXsdBoundTest() {
        XmlXsdBound bound = saveXmlXsdBoundUseCase.saveXsd("valid_user.xml", "user.xsd");

        Optional<XmlEntity> xmlEntity = xmlEntityService.findByName("valid_user.xml");
        Assert.isTrue(Objects.equals(xmlEntity.get(), bound.getXmlEntity()), "Error executing saveXmlXsdBoundUseCase.saveXsd");

        Optional<XsdEntity> xsdEntity = xsdEntityService.findByName("user.xsd");
        Assert.isTrue(Objects.equals(xsdEntity.get(), bound.getXsdEntity()), "Error executing saveXmlXsdBoundUseCase.saveXsd");

        WebApplicationException thrownBoundAlreadyExists = assertThrows(
                WebApplicationException.class,
                () -> saveXmlXsdBoundUseCase.saveXsd("valid_user.xml", "user.xsd"),
                "Expected saveXmlXsdBoundUseCase.saveXsd() to throw an exception, but it didn't");
        Assert.isTrue(Objects.equals(thrownBoundAlreadyExists.getMessage(), "Связь для xml документа уже существует: xml=valid_user.xml"),
                "Error executing saveXmlXsdBoundUseCase.saveXsd");
    }

    @Test
    @Transactional
    public void validateXmlTest() {
        XmlXsdBound bound = saveXmlXsdBoundUseCase.saveXsd("valid_user.xml", "user.xsd");

        XmlValid xmlValid = validateXmlUseCase.validateXml("valid_user.xml");
        Assert.isTrue(Objects.equals(xmlValid.getXmlEntity(), bound.getXmlEntity()), "Error executing validateXmlUseCase.validateXml");

        XmlXsdBound invalidBound = saveXmlXsdBoundUseCase.saveXsd("invalid_user.xml", "user.xsd");

        WebApplicationException thrownInvalidXml = assertThrows(
                WebApplicationException.class,
                () -> validateXmlUseCase.validateXml("invalid_user.xml"),
                "Expected validateXmlUseCase.validateXml() to throw an exception, but it didn't");

        Assert.isTrue(Objects.equals(thrownInvalidXml.getMessage(), "Ошибка валидации документа: xml=invalid_user.xml, xsd=user.xsd"),
                "Error executing validateXmlUseCase.validateXml");
    }

    @Test
    @Transactional
    public void saveXmlTest() {
        XmlXsdBound bound = saveXmlXsdBoundUseCase.saveXsd("valid_user.xml", "user.xsd");
        XmlValid xmlValid = validateXmlUseCase.validateXml("valid_user.xml");

        XmlXsdBound invalidBound = saveXmlXsdBoundUseCase.saveXsd("invalid_user.xml", "user.xsd");
        WebApplicationException thrownInvalidXml = assertThrows(
                WebApplicationException.class,
                () -> validateXmlUseCase.validateXml("invalid_user.xml"),
                "Expected validateXmlUseCase.validateXml() to throw an exception, but it didn't");
        Assert.isTrue(Objects.equals(thrownInvalidXml.getMessage(), "Ошибка валидации документа: xml=invalid_user.xml, xsd=user.xsd"),
                "Error executing validateXmlUseCase.validateXml");

        XmlProceed xmlProceed = saveXmlEntityUseCase.saveXml("valid_user.xml");
        Assert.isTrue(Objects.equals(xmlProceed.getXmlEntity(), bound.getXmlEntity()), "Error executing saveXmlEntityUseCase.saveXml");
        Assert.isTrue(Objects.equals(xmlValid.getXmlEntity(), bound.getXmlEntity()), "Error executing saveXmlEntityUseCase.saveXml");

        WebApplicationException thrownSaveInvalidXml = assertThrows(
                WebApplicationException.class,
                () -> saveXmlEntityUseCase.saveXml("invalid_user.xml"),
                "Expected saveXmlEntityUseCase.saveXml() to throw an exception, but it didn't");
        Assert.isTrue(Objects.equals(thrownSaveInvalidXml.getMessage(), "Файл invalid_user.xml помечен как невалидный"),
                "Error executing saveXmlEntityUseCase.saveXml");

    }
}
