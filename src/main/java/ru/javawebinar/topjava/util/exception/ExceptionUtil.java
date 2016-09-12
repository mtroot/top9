package ru.javawebinar.topjava.util.exception;

/**
 * @author Timur Muratov
 */
public class ExceptionUtil<T> {

    public static void checkedNotFoundWithId(boolean found, int id) {
        checkedNotFound(found, "id = " + id);
    }
    public static <T> T checkedNotFoundWithId(T object, int id){
        return checkedNotFound(object, "id = " + id);
    }

    public static <T> T checkedNotFound(T object, String msg) {
        checkedNotFound(object != null, msg);
        return object;
    }

    public static void checkedNotFound(boolean found, String msg) {
        if(!found) throw new NotFoundException("Object not found. " + msg);
    }
}
