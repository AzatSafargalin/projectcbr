package ru.cbr.project.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cbr.project.model.XmlEntity;
import ru.cbr.project.model.XmlXsdBound;
import ru.cbr.project.model.XsdEntity;
import ru.cbr.project.repository.XmlXsdBoundRepository;
import ru.cbr.project.service.XmlXsdBoundService;

@Service
public class XmlXsdBoundServiceImpl implements XmlXsdBoundService {

    private XmlXsdBoundRepository xmlXsdBoundRepository;

    @Autowired
    public void setXmlXsdBoundRepository(XmlXsdBoundRepository xmlXsdBoundRepository) {
        this.xmlXsdBoundRepository = xmlXsdBoundRepository;
    }

    @Override
    public XmlXsdBound save(XmlXsdBound xmlXsdBound) {
        return xmlXsdBoundRepository.save(xmlXsdBound);
    }

    @Override
    public Optional<XmlXsdBound> findByXmlEntityAndXsdEntity(final XmlEntity xmlEntity, final XsdEntity xsdEntity) {
        return xmlXsdBoundRepository.findByXmlEntityAndXsdEntity(xmlEntity, xsdEntity);
    }

    @Override
    public Optional<XmlXsdBound> findByXmlEntity(final XmlEntity xmlEntity) {
        return xmlXsdBoundRepository.findByXmlEntity(xmlEntity);
    }

}
