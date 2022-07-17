package ru.cbr.project.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cbr.project.model.XmlEntity;
import ru.cbr.project.model.XmlValid;
import ru.cbr.project.repository.XmlValidRepository;
import ru.cbr.project.service.XmlValidService;

@Service
public class XmlValidServiceImpl implements XmlValidService {

    private XmlValidRepository xmlValidRepository;

    @Autowired
    public void setXmlValidRepository(XmlValidRepository xmlValidRepository) {
        this.xmlValidRepository = xmlValidRepository;
    }

    @Override
    public XmlValid save(final XmlValid xmlValid) {
        return xmlValidRepository.save(xmlValid);
    }

    @Override
    public Optional<XmlValid> findByXmlEntity(final XmlEntity xmlEntity) {
        return xmlValidRepository.findByXmlEntity(xmlEntity);
    }

    @Override
    public void deleteByXmlEntity(final XmlEntity xmlEntity) {
        xmlValidRepository.deleteByXmlEntity(xmlEntity);
    }

}
