package ru.cbr.project.service;

import java.util.Optional;
import ru.cbr.project.model.XmlEntity;

/**
 *
 * @author Azat Safargalin
 */
public interface XmlEntityService {
    
    XmlEntity save(XmlEntity xmlEntity);
    
    Optional<XmlEntity> findById(Long id);
    
    Optional<XmlEntity> findByName(String xmlName);
    
    
}
