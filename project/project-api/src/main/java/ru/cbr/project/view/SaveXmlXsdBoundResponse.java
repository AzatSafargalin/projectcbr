package ru.cbr.project.view;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ru.cbr.project.core.ResponseStatus;

/**
 *
 * @author Azat Safargalin
 */
@XmlRootElement(name = "saveXmlXsdBoundResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saveXmlXsdBoundResponse", propOrder = {"status", "message"})
public class SaveXmlXsdBoundResponse extends ResponseBase implements Serializable {

    public SaveXmlXsdBoundResponse(ResponseStatus status, String message) {
        super(status, message);
    }
}
