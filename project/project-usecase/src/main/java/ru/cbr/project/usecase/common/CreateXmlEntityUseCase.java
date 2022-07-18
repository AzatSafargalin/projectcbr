package ru.cbr.project.usecase.common;

import ru.cbr.project.model.XmlEntity;

/**
 *
 * @author Azat Safargalin
 */
public interface CreateXmlEntityUseCase {

    /**
     * Создание сущности XML документа по названию файла
     *
     * @param xmlName
     * @return
     */
    XmlEntity createXmlEntity(String xmlName);
}
