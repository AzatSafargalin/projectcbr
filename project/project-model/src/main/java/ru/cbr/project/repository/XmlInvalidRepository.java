package ru.cbr.project.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.cbr.project.model.XmlEntity;
import ru.cbr.project.model.XmlInvalid;

/**
 *
 * @author Azat Safargalin
 */
public interface XmlInvalidRepository extends JpaRepository<XmlInvalid, Long> {

    Optional<XmlInvalid> findByXmlEntity(XmlEntity xmlEntity);
    
    void deleteByXmlEntity(XmlEntity xmlEntity);
}
