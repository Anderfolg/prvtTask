package com.anderfolg.daorest.entities.enums;

import lombok.Getter;

@Getter
public enum EntryStatus {
    ACTIVE("Active"),
    NOTED("Noted");
    private final String value;

    EntryStatus( String value ) {
        this.value = value;
    }
}