package ru.cbr.project.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cbr.project.model.XmlEntity;
import ru.cbr.project.model.XmlProceed;
import ru.cbr.project.repository.XmlProceedRepository;
import ru.cbr.project.service.XmlProceedService;

@Service
public class XmlProceedServiceImpl implements XmlProceedService {

    private XmlProceedRepository xmlProceedRepository;

    @Autowired
    public void setXmlProceedRepository(XmlProceedRepository xmlProceedRepository) {
        this.xmlProceedRepository = xmlProceedRepository;
    }

    @Override
    public XmlProceed save(final XmlProceed xmlProceed) {
        return xmlProceedRepository.save(xmlProceed);
    }

    @Override
    public void deleteByXmlEntity(final XmlEntity xmlEntity) {
        xmlProceedRepository.deleteByXmlEntity(xmlEntity);
    }

    @Override
    public Optional<XmlProceed> findByXmlEntity(final XmlEntity xmlEntity) {
        return xmlProceedRepository.findByXmlEntity(xmlEntity);
    }

}
