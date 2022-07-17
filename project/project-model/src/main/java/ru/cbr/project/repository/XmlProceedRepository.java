package ru.cbr.project.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.cbr.project.model.XmlEntity;
import ru.cbr.project.model.XmlProceed;

/**
 *
 * @author Azat Safargalin
 */
public interface XmlProceedRepository extends JpaRepository<XmlProceed, Long> {

    Optional<XmlProceed> findByXmlEntity(XmlEntity xmlEntity);
    
    void deleteByXmlEntity(XmlEntity xmlEntity);
}
