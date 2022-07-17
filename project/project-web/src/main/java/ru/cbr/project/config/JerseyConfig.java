package ru.cbr.project.config;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.jaxrs.xml.JacksonXMLProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;
import ru.cbr.project.web.DownloadResourceImpl;
import ru.cbr.project.web.SaveResourceImpl;
import ru.cbr.project.web.ValidateResourceImpl;

/**
 *
 * @author Azat Safargalin
 */
@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(Jackson2ObjectMapperBuilder builder) {
        register(SaveResourceImpl.class);
        register(ValidateResourceImpl.class);
        register(GenericExceptionMapper.class);
        register(DownloadResourceImpl.class);
        
        XmlMapper xmlMapper = new XmlMapper();
        builder.configure(xmlMapper);
        register(new JacksonXMLProvider(xmlMapper));
    }
}
