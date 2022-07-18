package ru.cbr.project.usecase;

import ru.cbr.project.model.XmlValid;

/**
 *
 * @author Azat Safargalin
 */
public interface ValidateXmlUseCase {

    /**
     * Валидация XML по имени файла через связь в таблице XML_XSD_BOUND
     * @param xmlName
     * @return
     */
    XmlValid validateXml(String xmlName);
}
