package ru.cbr.project.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@ToString(of = {"name"})
@NoArgsConstructor
@Table(name = "XML_ENTITIES")
public class XmlEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "SEQ_XML_ENTITIES_ID_GEN",
            strategy = GenerationType.TABLE)
    @TableGenerator(name = "SEQ_XML_ENTITIES_ID_GEN",
            table = "SEQUENCE",
            pkColumnValue = "SEQ_XML_ENTITIES_ID_GEN",
            valueColumnName = "SEQ_VALUE",
            pkColumnName = "SEQ_NAME")
    private Long id;

    @Basic
    private String name;

    public XmlEntity withName(String name) {
        this.setName(name);
        return this;
    }
}
