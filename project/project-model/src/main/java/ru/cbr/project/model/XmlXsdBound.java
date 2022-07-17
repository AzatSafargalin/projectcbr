package ru.cbr.project.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Azat Safargalin
 */
@Data
@Entity
@AllArgsConstructor()
@ToString(of = {"xmlEntity", "xsdEntity"})
@NoArgsConstructor
@Table(name = "XML_XSD_BOUND", indexes = @Index(name = "UNQ_XML_XSD_BOUND_XML_ENTITY", columnList = "XML_ENTITY_ID", unique = true))
public class XmlXsdBound implements Serializable {

    @Id
    @GeneratedValue(generator = "SEQ_XML_XSD_BOUND_ID_GEN",
            strategy = GenerationType.TABLE)
    @TableGenerator(name = "SEQ_XML_XSD_BOUND_ID_GEN",
            table = "SEQUENCE",
            pkColumnValue = "SEQ_XML_XSD_BOUND_ID_GEN",
            valueColumnName = "SEQ_VALUE",
            pkColumnName = "SEQ_NAME")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private XmlEntity xmlEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private XsdEntity xsdEntity;

    public XmlXsdBound withXmlEntity(XmlEntity xmlEntity) {
        this.setXmlEntity(xmlEntity);
        return this;
    }
    
    public XmlXsdBound withXsdEntity(XsdEntity xsdEntity) {
        this.setXsdEntity(xsdEntity);
        return this;
    }

}
