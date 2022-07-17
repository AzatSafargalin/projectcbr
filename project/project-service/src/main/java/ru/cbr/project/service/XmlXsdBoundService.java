package ru.cbr.project.service;

import java.util.Optional;
import ru.cbr.project.model.XmlEntity;
import ru.cbr.project.model.XmlXsdBound;
import ru.cbr.project.model.XsdEntity;

/**
 *
 * @author Azat Safargalin
 */
public interface XmlXsdBoundService {

    XmlXsdBound save(XmlXsdBound xmlXsdBound);

    Optional<XmlXsdBound> findByXmlEntityAndXsdEntity(XmlEntity xmlEntity, XsdEntity xsdEntity);
    
    Optional<XmlXsdBound> findByXmlEntity(XmlEntity xmlEntity);
    
}
