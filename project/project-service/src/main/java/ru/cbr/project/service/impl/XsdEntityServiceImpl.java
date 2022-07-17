package ru.cbr.project.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cbr.project.model.XsdEntity;
import ru.cbr.project.repository.XsdEntityRepository;
import ru.cbr.project.service.XsdEntityService;

@Service
public class XsdEntityServiceImpl implements XsdEntityService {

    private XsdEntityRepository xsdEntityRepository;

    @Autowired
    public void setXsdEntityRepository(XsdEntityRepository xsdEntityRepository) {
        this.xsdEntityRepository = xsdEntityRepository;
    }

    @Override
    public XsdEntity save(final XsdEntity xsdEntity) {
        return xsdEntityRepository.save(xsdEntity);
    }

    @Override
    public Optional<XsdEntity> findByName(final String name) {
        return xsdEntityRepository.findByName(name);
    }

}
