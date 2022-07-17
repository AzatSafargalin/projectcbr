package ru.cbr.project.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.cbr.project.model.XmlEntity;

/**
 *
 * @author Azat Safargalin
 */
public interface XmlEntityRepository extends JpaRepository<XmlEntity, Long> {

    Optional<XmlEntity> findByName(String name);

}
