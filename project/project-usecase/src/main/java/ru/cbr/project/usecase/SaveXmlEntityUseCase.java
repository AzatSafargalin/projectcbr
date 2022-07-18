package ru.cbr.project.usecase;

import ru.cbr.project.model.XmlProceed;

/**
 *
 * @author Azat Safargalin
 */
public interface SaveXmlEntityUseCase {

    /**
     * Сохранение XML файла в таблицу XML_PROCEED
     * @param xmlName
     * @return 
     */
    XmlProceed saveXml(String xmlName);
}
