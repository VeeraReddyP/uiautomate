package com.clickship.web.automation.exceptions;

public class AutomationException extends RuntimeException {
    /**
     * Instantiates a new AutomationException.
     */
    public AutomationException() {
        super();
    }

    /**
     * Instantiates a new AutomationException.
     *
     * @param message the message
     */
    public AutomationException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new AutomationException.
     *
     * @param cause the cause
     */
    public AutomationException(final Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new AutomationException.
     *
     * @param message the message
     * @param cause   the cause
     */
    public AutomationException(final String message, final Throwable cause) {
        super(message, cause.getCause());
    }
}