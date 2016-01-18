package org.projectlemon.intenseorange.model.utilities.exceptions;

/**
 * Created by linuslagerhjelm on 16-01-16.
 */
public class UnableToConnectException extends RuntimeException {

    public UnableToConnectException() {
        super();
    }

    public UnableToConnectException(String message) {
        super(message);
    }
}
