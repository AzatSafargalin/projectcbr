package ru.cbr.project.usecase.common;

import ru.cbr.project.model.XmlEntity;

/**
 *
 * @author Azat Safargalin
 */
public interface CreateXmlEntityUseCase {
    
    XmlEntity createXmlEntity(String xmlName);
}
