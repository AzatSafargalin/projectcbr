package ru.cbr.project.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.cbr.project.model.XsdEntity;

/**
 *
 * @author Azat Safargalin
 */
public interface XsdEntityRepository extends JpaRepository<XsdEntity, Long> {

    Optional<XsdEntity> findByName(String name);
}
