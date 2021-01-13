package com.atlas.entity;

import com.atlas.AtlasUtils;
import org.apache.atlas.model.instance.AtlasEntity;

import java.sql.Timestamp;
import java.util.Date;

import static org.apache.atlas.type.AtlasTypeUtil.*;

public class Port {
    public static AtlasEntity createSQLPort(String tableName, AtlasEntity avroSchema, AtlasEntity data) {
        AtlasEntity sqlPort = new AtlasEntity("sql_port");
        sqlPort.setAttribute("name", tableName);
        sqlPort.setAttribute("tableName", tableName);
        sqlPort.setAttribute("address", "jdbc://path/to/table/" + tableName);
        sqlPort.setAttribute("dataSchema", getAtlasObjectId(avroSchema));
        sqlPort.setAttribute(ATTRIBUTE_QUALIFIED_NAME, tableName + " SQL Port");
        sqlPort.setAttribute("description", "Description of this SQL Port");
        sqlPort.setAttribute("portType", PortType.SQL.getAtlasValue());
        sqlPort.setAttribute("createdAt", new Timestamp(2020, 8, 14, 0, 0, 0, 0));
        sqlPort.setAttribute("lastUpdatedAt", new Timestamp(2020, 8, 14, 0, 0, 0, 0));
        sqlPort.setRelationshipAttribute("data", getAtlasObjectId(data));
        return sqlPort;
    }

    public static AtlasEntity createBatchPort(String name, String fileFormat, AtlasEntity avroSchema, AtlasEntity data) {
        AtlasEntity batchPort = new AtlasEntity("batch_port");
        batchPort.setAttribute("name", name);
        batchPort.setAttribute("baseAddress", "adls://base/address/to/data/" + name + "." + fileFormat);
        batchPort.setAttribute("fileFormat", fileFormat);
        batchPort.setAttribute("dataSchema", getAtlasObjectId(avroSchema));
        batchPort.setAttribute(ATTRIBUTE_QUALIFIED_NAME, name + " Batch Port");
        batchPort.setAttribute("description", "Description of this SQL Port");
        batchPort.setAttribute("portType", PortType.BATCH.getAtlasValue());
        batchPort.setAttribute("createdAt", new Timestamp(2020, 8, 14, 0, 0, 0, 0));
        batchPort.setAttribute("lastUpdatedAt", new Timestamp(2020, 8, 14, 0, 0, 0, 0));
        batchPort.setRelationshipAttribute("data", getAtlasObjectId(data));
        return batchPort;
    }
}
