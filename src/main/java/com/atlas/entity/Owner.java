package com.atlas.entity;

import org.apache.atlas.model.instance.AtlasEntity;

import static org.apache.atlas.type.AtlasTypeUtil.ATTRIBUTE_QUALIFIED_NAME;

public class Owner {
    public static AtlasEntity getEntity(String id, String name, String email, OwnerType ownerType) {
        AtlasEntity owner = new AtlasEntity("owner");
        owner.setAttribute("id", id);
        owner.setAttribute("name", name);
        owner.setAttribute("email", email);
        owner.setAttribute("ownerType", ownerType.getAtlasValue());
        owner.setAttribute(ATTRIBUTE_QUALIFIED_NAME, email);
        return owner;
    }
}
