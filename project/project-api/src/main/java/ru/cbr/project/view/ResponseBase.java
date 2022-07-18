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
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "response", propOrder = {"status", "message"})
public class ResponseBase implements Serializable {

    private final ResponseStatus status;

    private final String message;

    public ResponseBase(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
