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
@XmlRootElement(name = "downloadResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "downloadResponse", propOrder = {"status", "message", "link"})
public class DownloadResponse extends ResponseBase implements Serializable {

    private final String link;

    public DownloadResponse(ResponseStatus status, String message, String link) {
        super(status, message);
        this.link = link;
    }

    public String getLink() {
        return link;
    }

}
