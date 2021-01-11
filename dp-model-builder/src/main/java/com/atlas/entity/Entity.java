package com.atlas.entity;

import org.apache.atlas.model.instance.AtlasEntity;

import java.util.ArrayList;
import java.util.List;

abstract public class Entity {
    protected ArrayList<AtlasEntity> referredEntities;

    protected Entity() {
        referredEntities = new ArrayList<>();
    }

    abstract public AtlasEntity createEntity(String name);

    //This method should only get called after createEntity() to fetch the transient entities
    public List<AtlasEntity> getReferredEntities() {
        return referredEntities;
    }
}
