package tools;

public class TaskTypeEnumException extends Exception {
    private TaskTypeEnumException(String message) {
        super(message);
    }

    public static TaskTypeEnumException valueWasNotFoundInEnum(String message) {
        return new TaskTypeEnumException(message);
    }
}