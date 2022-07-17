package ru.cbr.project.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cbr.project.model.XmlEntity;
import ru.cbr.project.model.XmlInvalid;
import ru.cbr.project.repository.XmlInvalidRepository;
import ru.cbr.project.service.XmlInvalidService;

@Service
public class XmlInvalidServiceImpl implements XmlInvalidService {

    private XmlInvalidRepository xmlInvalidRepository;

    @Autowired
    public void setXmlInvalidRepository(XmlInvalidRepository xmlInvalidRepository) {
        this.xmlInvalidRepository = xmlInvalidRepository;
    }

    @Override
    public XmlInvalid save(final XmlInvalid xmlInvalid) {
        return xmlInvalidRepository.save(xmlInvalid);
    }

    @Override
    public Optional<XmlInvalid> findByXmlEntity(final XmlEntity xmlEntity) {
        return xmlInvalidRepository.findByXmlEntity(xmlEntity);
    }

    @Override
    public void deleteByXmlEntity(final XmlEntity xmlEntity) {
        xmlInvalidRepository.deleteByXmlEntity(xmlEntity);
    }

}
