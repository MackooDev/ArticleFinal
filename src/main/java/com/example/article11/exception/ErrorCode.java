package com.example.article11.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    NO_SUCH_ARTICLE_FOUND(2000, "Nie znaleziono nauczyciela o podanym id");

    private final int code;
    private final String description;

    ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

}
