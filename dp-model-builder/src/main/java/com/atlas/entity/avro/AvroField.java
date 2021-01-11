package com.atlas.entity.avro;

import com.atlas.AtlasUtils;
import com.atlas.entity.Entity;
import org.apache.atlas.model.instance.AtlasEntity;

import static org.apache.atlas.type.AtlasTypeUtil.ATTRIBUTE_QUALIFIED_NAME;

public class AvroField extends Entity {

    public AtlasEntity createEntity(String name, AtlasEntity avroType) {
        AtlasEntity avroField = new AtlasEntity("avro_field");
        avroField.setAttribute("name", name);
        avroField.setAttribute(ATTRIBUTE_QUALIFIED_NAME, name);
        avroField.setRelationshipAttribute("type", AtlasUtils.getAtlasObjectIds(avroType));

        referredEntities.add(avroField);
        return new AtlasEntity(avroField);
    }

    @Override
    public AtlasEntity createEntity(String name) {
        return null;
    }
}
