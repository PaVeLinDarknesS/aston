package ru.aston.hometask.hw3;

import java.io.IOException;

public class MyIOException extends IOException {
    public MyIOException(String message) {
        super(message);
    }

    public MyIOException(String message, Throwable cause) {
        super(message, cause);
    }
}
