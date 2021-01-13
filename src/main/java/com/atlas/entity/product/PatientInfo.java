package com.atlas.entity.product;

import com.atlas.AtlasUtils;
import com.atlas.entity.Entity;
import com.atlas.entity.Owner;
import com.atlas.entity.OwnerType;
import com.atlas.entity.avro.AvroType;
import com.atlas.entity.data.Demographics;
import com.atlas.entity.data.HealthRecords;
import com.atlas.entity.data.PatientCountByDisease;
import com.atlas.entity.data.PatientCountByLocation;
import org.apache.atlas.model.instance.AtlasEntity;

import java.util.ArrayList;
import java.util.List;

import static org.apache.atlas.type.AtlasTypeUtil.ATTRIBUTE_QUALIFIED_NAME;
import static org.apache.atlas.type.AtlasTypeUtil.getAtlasObjectIds;

public class PatientInfo extends Entity {
    private AvroType avroType;

    public PatientInfo(AvroType avroType) {
        this.avroType = avroType;
    }

    public AtlasEntity createEntity(String name) {
        referredEntities = new ArrayList<AtlasEntity>();

        AtlasEntity owner = createOwner("John Doe", "john@doe.com", OwnerType.BUSINESS);
        List<AtlasEntity> inputs = createInputs();
        List<AtlasEntity> outputs = createOutputs();

        AtlasEntity dataProduct = new AtlasEntity("data_product");
        dataProduct.setAttribute("id", name + "_id");
        dataProduct.setAttribute("name", name);
        dataProduct.setAttribute("machineName", name + "_short_name");
        dataProduct.setAttribute("description", "Description of " + name + " data product");
        dataProduct.setAttribute("monitoringDashboardUrl", "http://monitoring.dashboard/" + name);
        dataProduct.setAttribute("sourceCodeUrl", "http://dev.azure.com/source/" + name);
        dataProduct.setAttribute("owner", AtlasUtils.getAtlasObjectIds(owner));
        dataProduct.setRelationshipAttribute("inputs", getAtlasObjectIds(inputs));
        dataProduct.setRelationshipAttribute("outputs", getAtlasObjectIds(outputs));
        dataProduct.setAttribute(ATTRIBUTE_QUALIFIED_NAME, name);

        return dataProduct;
    }

    private List<AtlasEntity> createInputs() {
        ArrayList<AtlasEntity> entities = new ArrayList<AtlasEntity>();
        Demographics demographics = new Demographics(avroType);
        HealthRecords healthRecords = new HealthRecords(avroType);

        entities.add(demographics.createEntity("demographics"));
        entities.add(healthRecords.createEntity("health_records"));

        referredEntities.addAll(demographics.getReferredEntities());
        referredEntities.addAll(healthRecords.getReferredEntities());
        referredEntities.addAll(entities);
        return entities;
    }

    private List<AtlasEntity> createOutputs() {
        ArrayList<AtlasEntity> entities = new ArrayList<AtlasEntity>();
        PatientCountByDisease patientCountByDisease = new PatientCountByDisease(avroType);
        PatientCountByLocation patientCountByLocation = new PatientCountByLocation(avroType);

        entities.add(patientCountByDisease.createEntity("patient_count_by_disease"));
        entities.add(patientCountByLocation.createEntity("patient_count_by_location"));

        referredEntities.addAll(patientCountByDisease.getReferredEntities());
        referredEntities.addAll(patientCountByLocation.getReferredEntities());
        referredEntities.addAll(entities);
        return entities;
    }

    private AtlasEntity createOwner(String name, String email, OwnerType ownerType) {
        AtlasEntity owner = Owner.getEntity(name + "_id", name, email, ownerType);
        referredEntities.add(owner);
        return owner;
    }
}
