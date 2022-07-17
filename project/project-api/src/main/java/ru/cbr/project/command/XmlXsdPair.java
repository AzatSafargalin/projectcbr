package ru.cbr.project.command;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
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
public class XmlXsdPair implements Serializable {

    @NotNull(message = "Введено некорректное имя для xml.")
    private String xmlName;

    @NotNull(message = "Введено некорректное имя для xsd.")
    private String xsdName;

    public XmlXsdPair() {
    }

    public XmlXsdPair(String xmlName, String xsdName) {
        this.xmlName = xmlName;
        this.xsdName = xsdName;
    }

    public String getXmlName() {
        return xmlName;
    }

    public void setXmlName(String xmlName) {
        this.xmlName = xmlName;
    }

    public String getXsdName() {
        return xsdName;
    }

    public void setXsdName(String xsdName) {
        this.xsdName = xsdName;
    }

}
