package ru.cbr.project.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.cbr.project.model.XmlEntity;
import ru.cbr.project.model.XmlXsdBound;
import ru.cbr.project.model.XsdEntity;

/**
 *
 * @author Azat Safargalin
 */
public interface XmlXsdBoundRepository extends JpaRepository<XmlXsdBound, Long> {

    Optional<XmlXsdBound> findByXmlEntityAndXsdEntity(XmlEntity xmlEntity, XsdEntity xsdEntity);
    
    Optional<XmlXsdBound> findByXmlEntity(XmlEntity xmlEntity);
    
}
