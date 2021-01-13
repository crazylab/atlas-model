package com.atlas.entity;

public enum OwnerType {
    BUSINESS("Business"),
    TECHNICAL("Technical"),
    DATA_STEWARD("DataSteward");

    private String atlasEnumValue;

    OwnerType(String atlasEnumValue) {
        this.atlasEnumValue = atlasEnumValue;
    }

    public String getAtlasValue() {
        return atlasEnumValue;
    }
}