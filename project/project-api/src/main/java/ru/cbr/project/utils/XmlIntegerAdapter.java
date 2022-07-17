package ru.cbr.project.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.apache.logging.log4j.util.Strings;

/**
 *
 * @author Azat Safargalin
 */
public class XmlIntegerAdapter extends XmlAdapter<String, Integer> {

    @Override
    public String marshal(Integer value) throws Exception {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    @Override
    public Integer unmarshal(String value) throws Exception {
        if (Strings.isBlank(value)) {
            return null;
        }
        return Integer.valueOf(value);
    }
}
