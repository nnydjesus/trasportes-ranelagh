package ar.com.nny.base.configuration;

import ar.com.nny.base.exception.NonBusinessException;

/**
 * This exception is similar to reflections InvocationTargetException
 *
 * @author Claudio
 */
public class CodeBlockTargetException extends NonBusinessException {

    public CodeBlockTargetException(Throwable cause) {
        super(cause);
    }

}
