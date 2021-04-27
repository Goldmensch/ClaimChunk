package com.cjburkey.claimchunk.cmd;

import lombok.Getter;

public enum Path {
    COMMAND("commands"),
    ARGUMENT("arguments");

    @Getter
    private final String value;

    Path(String v) {
        value = v + ".";
    }
}
