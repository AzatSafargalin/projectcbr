package ru.cbr.project.service;

import java.util.Optional;
import ru.cbr.project.model.XsdEntity;

/**
 *
 * @author Azat Safargalin
 */
public interface XsdEntityService {

    XsdEntity save(XsdEntity xsdEntity);

    Optional<XsdEntity> findByName(String name);

}
