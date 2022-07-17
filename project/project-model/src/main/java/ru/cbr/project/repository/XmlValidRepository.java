package ru.cbr.project.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.cbr.project.model.XmlEntity;
import ru.cbr.project.model.XmlValid;

/**
 *
 * @author Azat Safargalin
 */
public interface XmlValidRepository extends JpaRepository<XmlValid, Long> {

    Optional<XmlValid> findByXmlEntity (XmlEntity xmlEntity);
    
    void deleteByXmlEntity (XmlEntity xmlEntity);
}
