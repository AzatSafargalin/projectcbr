package ru.cbr.project.usecase.common;

import ru.cbr.project.model.XsdEntity;

/**
 *
 * @author Azat Safargalin
 */
public interface CreateXsdEntityUseCase {

    /**
     * Создание сущности XSD документа по названию файла
     *
     * @param xmlName
     * @return
     */
    XsdEntity createXsdEntity(String xsdName);
}
