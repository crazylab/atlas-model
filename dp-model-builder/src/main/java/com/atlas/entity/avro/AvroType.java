package com.atlas.entity.avro;

import com.atlas.entity.Entity;
import org.apache.atlas.model.instance.AtlasEntity;

import static org.apache.atlas.type.AtlasTypeUtil.ATTRIBUTE_QUALIFIED_NAME;

public class AvroType extends Entity {
    public AtlasEntity intType = null;
    public AtlasEntity longType = null;
    public AtlasEntity stringType = null;
    public AtlasEntity locationType = null;
    public AtlasEntity dateType = null;

    private AtlasEntity createTypes(String name, String type) {
        AtlasEntity avroType = new AtlasEntity("avro_type");
        avroType.setAttribute("name", name);
        avroType.setAttribute("type", type);
        avroType.setAttribute(ATTRIBUTE_QUALIFIED_NAME, name);
        referredEntities.add(avroType);
        return avroType;
    }

    @Override
    public AtlasEntity createEntity(String name) {
        this.intType = createTypes("int", "int");
        this.longType = createTypes("long", "long");
        this.stringType = createTypes("string", "string");
        this.locationType = createTypes("location", "coordinate");
        this.dateType = createTypes("date", "timestamp");
        return null;
    }
}
