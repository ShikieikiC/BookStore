package org.shiki.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResEnum {
    SUCCESS(200, "ok"),
    FAILED(null, "error");

    private final Integer code;
    private final String msg;


}
