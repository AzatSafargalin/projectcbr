package ru.cbr.project.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Azat Safargalin
 */
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseType", propOrder = {"status", "message",})
public class ResponseView {

    private final Integer status;

    private final String message;

    private final String link;

    public ResponseView(Integer status, String message, String link) {
        this.status = status;
        this.message = message;
        this.link = link;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getLink() {
        return link;
    }

}
