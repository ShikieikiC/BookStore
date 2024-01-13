package org.shiki.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class OutOfStoreException extends RuntimeException {
    private final List<String> bookNames;

    public OutOfStoreException(List<String> bookNames) {
        this.bookNames = bookNames;
    }
}
