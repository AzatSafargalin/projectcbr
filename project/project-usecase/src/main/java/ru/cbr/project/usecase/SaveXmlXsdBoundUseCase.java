package ru.cbr.project.usecase;

import ru.cbr.project.model.XmlXsdBound;

/**
 *
 * @author Azat Safargalin
 */
public interface SaveXmlXsdBoundUseCase {

    /**
     * Создание связи XML <-> XSD
     * @param xmlName
     * @param xsdName
     * @return 
     */
    XmlXsdBound saveXsd(String xmlName, String xsdName); 
}
