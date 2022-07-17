package ru.cbr.project.usecase.common;

import ru.cbr.project.model.XsdEntity;

/**
 *
 * @author Azat Safargalin
 */
public interface CreateXsdEntityUseCase {
    
    XsdEntity createXsdEntity(String xsdName);
}
