package ru.cbr.project.web;

import java.util.Objects;
import javax.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import ru.cbr.project.api.ValidateResource;
import ru.cbr.project.model.XmlValid;
import ru.cbr.project.usecase.ValidateXmlUseCase;

/**
 *
 * @author Azat Safargalin
 */
@Path("validate")
public class ValidateResourceImpl implements ValidateResource {

    private ValidateXmlUseCase validateXmlUseCase;

    @Autowired
    public void setValidateXmlUseCase(ValidateXmlUseCase validateXmlUseCase) {
        this.validateXmlUseCase = validateXmlUseCase;
    }

    @Override
    public ru.cbr.project.view.ResponseView validateXml(final ru.cbr.project.command.XmlEntity xmlEntity) {
        XmlValid xmlValid = validateXmlUseCase.validateXml(xmlEntity.getXmlName());
        Objects.requireNonNull(xmlValid, "Ошибка работы сервиса, обратитесь к администратору.");
        return new ru.cbr.project.view.ResponseView(200, "Валидация прошла успешно. Создана сущность XmlValid: id=" + xmlValid.getId(), null);
    }

}
