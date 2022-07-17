package ru.cbr.project.usecase;

import ru.cbr.project.model.XmlProceed;

/**
 *
 * @author Azat Safargalin
 */
public interface SaveXmlEntityUseCase {

    XmlProceed saveXml(String xmlName);
}
