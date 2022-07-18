package ru.cbr.project.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Azat Safargalin
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor()
@ToString(of = {"xmlEntity"})
@Table(name = "XML_VALID", indexes = @Index(name = "UNQ_XML_VALID_XML_ENTITY", columnList = "XML_ENTITY_ID", unique = true))
public class XmlValid implements Serializable {

    @Id
    @GeneratedValue(generator = "SEQ_XML_VALID_ID_GEN",
            strategy = GenerationType.TABLE)
    @TableGenerator(name = "SEQ_XML_VALID_ID_GEN",
            table = "SEQUENCE",
            pkColumnValue = "SEQ_XML_VALID_ID_GEN",
            valueColumnName = "SEQ_VALUE",
            pkColumnName = "SEQ_NAME")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private XmlEntity xmlEntity;

    public XmlValid withXmlEntity(XmlEntity xmlEntity) {
        this.setXmlEntity(xmlEntity);
        return this;
    }

}
