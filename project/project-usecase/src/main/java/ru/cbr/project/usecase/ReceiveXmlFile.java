package ru.cbr.project.usecase;

import ru.cbr.project.core.FileStatus;

/**
 *
 * @author Azat Safargalin
 */
public interface ReceiveXmlFile {

    byte[] getFile(String xmlName);
    
    FileStatus getFileStatus(String xmlName);

}
