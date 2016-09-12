package ru.javawebinar.topjava.util.exception;

/**
 * @author Timur Muratov
 */
public class NotFoundException extends RuntimeException{
    public NotFoundException(String msg) {
        super(msg);
    }
}
