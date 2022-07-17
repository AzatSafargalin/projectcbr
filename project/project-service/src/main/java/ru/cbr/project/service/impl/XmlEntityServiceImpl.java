package ru.cbr.project.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cbr.project.model.XmlEntity;
import ru.cbr.project.repository.XmlEntityRepository;
import ru.cbr.project.service.XmlEntityService;

@Service
public class XmlEntityServiceImpl implements XmlEntityService {

    private XmlEntityRepository xmlEntityRepository;

    @Autowired
    public void setXmlEntityRepository(XmlEntityRepository xmlEntityRepository) {
        this.xmlEntityRepository = xmlEntityRepository;
    }

    @Override
    public XmlEntity save(XmlEntity xmlEntity) {
        return xmlEntityRepository.save(xmlEntity);
    }

    @Override
    public Optional<XmlEntity> findById(final Long id) {
        return xmlEntityRepository.findById(id);
    }

    @Override
    public Optional<XmlEntity> findByName(final String xmlName) {
        return xmlEntityRepository.findByName(xmlName);
    }

}
