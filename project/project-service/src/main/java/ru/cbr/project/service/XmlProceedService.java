package ru.cbr.project.service;

import java.util.Optional;
import ru.cbr.project.model.XmlEntity;
import ru.cbr.project.model.XmlProceed;

/**
 *
 * @author Azat Safargalin
 */
public interface XmlProceedService {

    XmlProceed save(XmlProceed xmlProceed);
    
    void deleteByXmlEntity(XmlEntity xmlEntity);
    
    Optional<XmlProceed> findByXmlEntity(XmlEntity xmlEntity);
}
