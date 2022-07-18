package ru.cbr.project.usecase;

import ru.cbr.project.core.FileStatus;

/**
 *
 * @author Azat Safargalin
 */
public interface ReceiveXmlFile {

    /**
     * Получение файла по имени
     *
     * @param xmlName
     * @return
     */
    byte[] getFile(String xmlName);

    /**
     * Получение статуса файла и ссылки для скачивания
     *
     * @param xmlName
     * @return
     */
    FileStatus getFileStatus(String xmlName);

}
