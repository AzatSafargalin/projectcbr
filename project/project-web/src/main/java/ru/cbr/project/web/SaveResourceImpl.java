package ru.cbr.project.web;

import javax.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.cbr.project.api.SaveResource;
import ru.cbr.project.model.XmlProceed;
import ru.cbr.project.model.XmlXsdBound;
import ru.cbr.project.usecase.SaveXmlEntityUseCase;
import ru.cbr.project.usecase.SaveXmlXsdBoundUseCase;

/**
 *
 * @author Azat Safargalin
 */
@Slf4j
@Path("save")
public class SaveResourceImpl implements SaveResource {

    private SaveXmlEntityUseCase saveXmlEntityUseCase;

    @Autowired
    public void setSaveXmlEntityUseCase(SaveXmlEntityUseCase saveXmlEntityUseCase) {
        this.saveXmlEntityUseCase = saveXmlEntityUseCase;
    }

    private SaveXmlXsdBoundUseCase saveXmlXsdBoundUseCase;

    @Autowired
    public void setSaveXmlXsdBoundUseCase(SaveXmlXsdBoundUseCase saveXmlXsdBoundUseCase) {
        this.saveXmlXsdBoundUseCase = saveXmlXsdBoundUseCase;
    }

    @Override
    public ru.cbr.project.view.ResponseView saveXml(final ru.cbr.project.command.XmlEntity xmlEntity) {
        XmlProceed xmlProceed = saveXmlEntityUseCase.saveXml(xmlEntity.getXmlName());
        return new ru.cbr.project.view.ResponseView(200, "Сохранение пары xml и xsl прошла успешно. Создана сущность XmlProceed: id=" + xmlProceed.getId(), null);
    }

    @Override
    public ru.cbr.project.view.ResponseView saveXmlXsdBound(final ru.cbr.project.command.XmlXsdPair xmlXsdPair) {
        XmlXsdBound xmlXsdBound = saveXmlXsdBoundUseCase.saveXsd(xmlXsdPair.getXmlName(), xmlXsdPair.getXsdName());
        return new ru.cbr.project.view.ResponseView(200, "Сохранение пары xml и xsl прошла успешно. Создана сущность XmlXsdBound: id=" + xmlXsdBound.getId(), null);
    }

}
