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
@XmlRootElement(name = "saveXmlResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saveXmlResponse", propOrder = {"status", "message"})
public class SaveXmlResponse extends ResponseBase implements Serializable {

    public SaveXmlResponse(ResponseStatus status, String message) {
        super(status, message);
    }
}
