import com.atlas.entity.avro.AvroType;
import com.atlas.entity.product.PatientInfo;
import org.apache.atlas.AtlasClientV2;
import org.apache.atlas.AtlasServiceException;
import org.apache.atlas.model.instance.AtlasEntity;
import org.apache.atlas.model.instance.AtlasEntity.AtlasEntitiesWithExtInfo;
import org.apache.atlas.model.instance.EntityMutationResponse;
import org.apache.atlas.type.AtlasType;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        AvroType avroType = new AvroType();
        avroType.createEntity("");

        PatientInfo patientInfo = new PatientInfo(avroType);
        AtlasEntity patientInfoEntity = patientInfo.createEntity("patient_info");

        List<AtlasEntity> allEntities = new ArrayList<AtlasEntity>();
        allEntities.addAll(avroType.getReferredEntities());
        allEntities.addAll(patientInfo.getReferredEntities());
        allEntities.add(patientInfoEntity);

        AtlasEntitiesWithExtInfo entityPayload = new AtlasEntity.AtlasEntitiesWithExtInfo();
        entityPayload.setEntities(allEntities);


        System.out.println(AtlasType.toJson(entityPayload));

//        String[] cred = new String[]{"admin", "admin"};
//        String[] baseUrls = {"http://localhost:21000"};
//        AtlasClientV2 atlasClient = new AtlasClientV2(baseUrls, cred);
//
//        EntityMutationResponse response = null;
//        try {
//            response = atlasClient.createEntities(entityPayload);
//        } catch (AtlasServiceException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(response);
    }
}
