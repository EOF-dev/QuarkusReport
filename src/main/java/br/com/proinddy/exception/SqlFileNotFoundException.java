package br.com.proinddy.exception;

import java.io.IOException;
import java.io.Serializable;

public class SqlFileNotFoundException extends IOException implements Serializable {

    private static final long serialVersionUID = 1L;

    public SqlFileNotFoundException() {
    }

    public SqlFileNotFoundException(String message) {
        super(message);
    }

    public SqlFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlFileNotFoundException(Throwable cause) {
        super(cause);
    }

}
