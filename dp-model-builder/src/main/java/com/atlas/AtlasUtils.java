package com.atlas;

import org.apache.atlas.model.instance.AtlasEntity;
import org.apache.atlas.model.instance.AtlasObjectId;
import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

import static org.apache.atlas.type.AtlasTypeUtil.getAtlasObjectId;

public class AtlasUtils {
    public static List<AtlasObjectId> getAtlasObjectIds(AtlasEntity... entities) {
        List<AtlasObjectId> entityIds = new ArrayList<>();
        if (ArrayUtils.isNotEmpty(entities)) {
            for (AtlasEntity entity : entities) {
                entityIds.add(getAtlasObjectId(entity));
            }
        }
        return entityIds;
    }
}
