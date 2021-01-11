package com.atlas.entity.data;

import com.atlas.AtlasUtils;
import com.atlas.entity.Entity;
import com.atlas.entity.Port;
import com.atlas.entity.avro.AvroField;
import com.atlas.entity.avro.AvroSchema;
import com.atlas.entity.avro.AvroType;
import org.apache.atlas.model.instance.AtlasEntity;

import java.util.ArrayList;

import static org.apache.atlas.type.AtlasTypeUtil.ATTRIBUTE_QUALIFIED_NAME;

public class Demographics extends Entity {
    private AvroType avroType;

    public Demographics(AvroType avroType) {
        super();
        this.avroType = avroType;
    }

    public AtlasEntity createEntity(String name) {
        referredEntities = new ArrayList<AtlasEntity>();


        AtlasEntity data = new AtlasEntity("data");
        data.setAttribute("name", name);
        data.setAttribute("description", "Description of " + name + " data");
        data.setAttribute("totalRecordCount", 0);
        data.setAttribute("adGroup", name + " AD Group");
        data.setAttribute("sourceSystem", name + "Default Source");
        data.setAttribute("version", "1.0");
        data.setAttribute(ATTRIBUTE_QUALIFIED_NAME, name);

        AtlasEntity port = createPort(name + "_table", data);
        return data;
    }

    private AtlasEntity createAvroSchema(String name) {
        ArrayList<AtlasEntity> fields = new ArrayList<AtlasEntity>();

        AvroField avroField = new AvroField();
        fields.add(avroField.createEntity("id", avroType.stringType));
        fields.add(avroField.createEntity("name", avroType.stringType));
        fields.add(avroField.createEntity("age", avroType.intType));
        fields.add(avroField.createEntity("location", avroType.locationType));

        AtlasEntity avroSchema = AvroSchema.createEntity(name, "default", fields);
        referredEntities.add(avroSchema);
        referredEntities.addAll(avroField.getReferredEntities());
        return avroSchema;
    }

    private AtlasEntity createPort(String name, AtlasEntity data) {
        AtlasEntity sqlPort = Port.createSQLPort(name, createAvroSchema(name), data);
        referredEntities.add(sqlPort);
        return sqlPort;
    }
}
