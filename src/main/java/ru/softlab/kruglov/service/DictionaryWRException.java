package ru.softlab.kruglov.service;

import java.io.IOException;

public class DictionaryWRException extends Exception {
    IOException exception;


    public DictionaryWRException() {
    }

    public DictionaryWRException(String message) {
        super(message);
    }

    public DictionaryWRException(String message, Throwable cause) {
        super(message, cause);
    }

    public DictionaryWRException(IOException exception) {
        super(exception);
        this.exception = exception;
    }

    public DictionaryWRException(Throwable cause) {
        super(cause);
    }

    public boolean isIOException() {
        return exception != null;
    }

    public IOException getIOException() {
        return exception;
    }


}
