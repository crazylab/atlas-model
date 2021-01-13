package com.atlas.entity.avro;

import org.apache.atlas.model.instance.AtlasEntity;

import java.util.List;

import static org.apache.atlas.type.AtlasTypeUtil.ATTRIBUTE_QUALIFIED_NAME;
import static org.apache.atlas.type.AtlasTypeUtil.getAtlasObjectIds;

public class AvroSchema {
    public static AtlasEntity createEntity(String name, String namespace, List<AtlasEntity> avroFields) {
        AtlasEntity avroSchema = new AtlasEntity("avro_schema");
        avroSchema.setAttribute("name", name);
        avroSchema.setAttribute("namespace", namespace);
        avroSchema.setAttribute("type", "struct");
        avroSchema.setAttribute(ATTRIBUTE_QUALIFIED_NAME, name);
        avroSchema.setRelationshipAttribute("fields", getAtlasObjectIds(avroFields));
        return avroSchema;
    }
}