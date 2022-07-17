package ru.cbr.project.usecase;

import ru.cbr.project.model.XmlXsdBound;

/**
 *
 * @author Azat Safargalin
 */
public interface SaveXmlXsdBoundUseCase {

    XmlXsdBound saveXsd(String xmlName, String xsdName); 
}
