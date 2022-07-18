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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Azat Safargalin
 */
@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor()
@ToString(of = {"name"})
@Table(name = "XSD_ENTITIES")
public class XsdEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "SEQ_XSD_ENTITIES_ID_GEN",
            strategy = GenerationType.TABLE)
    @TableGenerator(name = "SEQ_XSD_ENTITIES_ID_GEN",
            table = "SEQUENCE",
            pkColumnValue = "SEQ_XSD_ENTITIES_ID_GEN",
            valueColumnName = "SEQ_VALUE",
            pkColumnName = "SEQ_NAME")
    private Long id;

    @Basic
    private String name;

    public XsdEntity withName(String name) {
        this.setName(name);
        return this;
    }
}
