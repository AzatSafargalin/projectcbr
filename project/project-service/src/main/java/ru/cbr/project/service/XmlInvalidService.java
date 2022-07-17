package ru.cbr.project.service;

import java.util.Optional;
import ru.cbr.project.model.XmlEntity;
import ru.cbr.project.model.XmlInvalid;

/**
 *
 * @author Azat Safargalin
 */
public interface XmlInvalidService {

    XmlInvalid save(XmlInvalid xmlInvalid);
    
    void deleteByXmlEntity(XmlEntity xmlEntity);
    
    Optional<XmlInvalid> findByXmlEntity(XmlEntity xmlEntity);
}
