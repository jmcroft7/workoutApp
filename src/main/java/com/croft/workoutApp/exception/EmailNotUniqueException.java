package com.croft.workoutApp.exception;

public class EmailNotUniqueException extends Throwable {
    public EmailNotUniqueException(String message) { super(message); }

    public EmailNotUniqueException(String message, Throwable cause) { super(message, cause); }
}
