package ru.cbr.project.service;

import java.util.Optional;
import ru.cbr.project.model.XmlEntity;
import ru.cbr.project.model.XmlValid;

/**
 *
 * @author Azat Safargalin
 */
public interface XmlValidService {

    XmlValid save(XmlValid xmlValid);
    
    void deleteByXmlEntity(XmlEntity xmlEntity);

    Optional<XmlValid> findByXmlEntity(XmlEntity xmlEntity);

}
