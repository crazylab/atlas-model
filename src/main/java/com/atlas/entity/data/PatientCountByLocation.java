package com.atlas.entity.data;

import com.atlas.entity.Entity;
import com.atlas.entity.Port;
import com.atlas.entity.avro.AvroField;
import com.atlas.entity.avro.AvroSchema;
import com.atlas.entity.avro.AvroType;
import org.apache.atlas.model.instance.AtlasEntity;

import java.util.ArrayList;
import java.util.List;

import static org.apache.atlas.type.AtlasTypeUtil.ATTRIBUTE_QUALIFIED_NAME;
import static org.apache.atlas.type.AtlasTypeUtil.getAtlasObjectIds;

public class PatientCountByLocation extends Entity {
    private AvroType avroType;

    public PatientCountByLocation(AvroType avroType) {
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

        createPorts(name, data);
        return data;
    }

    private AtlasEntity createAvroSchema(String name) {
        ArrayList<AtlasEntity> fields = new ArrayList<AtlasEntity>();
        AvroField avroField = new AvroField();
        fields.add(avroField.createEntity("location", avroType.stringType));
        fields.add(avroField.createEntity("count", avroType.longType));

        AtlasEntity avroSchema = AvroSchema.createEntity(name, "default", fields);
        referredEntities.add(avroSchema);
        referredEntities.addAll(avroField.getReferredEntities());
        return avroSchema;
    }

    private List<AtlasEntity> createPorts(String name, AtlasEntity data) {
        ArrayList<AtlasEntity> ports = new ArrayList<AtlasEntity>();
        AtlasEntity avroSchema = createAvroSchema(name);
        AtlasEntity sqlPort = Port.createSQLPort(name + "_table", avroSchema, data);
        AtlasEntity batchPort = Port.createSQLPort(name, avroSchema, data);
        ports.add(sqlPort);
        ports.add(batchPort);
        referredEntities.addAll(ports);
        return ports;
    }
}
