package ru.cbr.project.command;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Azat Safargalin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xmlXsdPair")
@XmlType(name = "xmlXsdPairType", propOrder = {"xmlName", "xsdName",})
public class XmlEntity implements Serializable {

    @NotBlank(message = "Введено некорректное имя для xml.")
    private String xmlName;

    public XmlEntity() {
    }

    public void setXmlName(String xmlName) {
        this.xmlName = xmlName;
    }

    public String getXmlName() {
        return xmlName;
    }

}
