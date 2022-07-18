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
@XmlRootElement(name = "validateResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validateResponse", propOrder = {"status", "message"})
public class ValidateResponse extends ResponseBase implements Serializable{

    public ValidateResponse(ResponseStatus status, String message) {
        super(status, message);
    }
}
