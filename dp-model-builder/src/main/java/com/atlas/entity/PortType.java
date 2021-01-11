package com.atlas.entity;

public enum PortType {
    SQL("SQLPort"),
    BATCH("BatchPort");

    private String atlasEnumValue;

    PortType(String atlasEnumValue) {
        this.atlasEnumValue = atlasEnumValue;
    }

    public String getAtlasValue() {
        return atlasEnumValue;
    }
}