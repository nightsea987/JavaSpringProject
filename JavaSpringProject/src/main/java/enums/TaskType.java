package enums;

import lombok.Getter;

@Getter
public enum TaskType {
    NEW_FUNCTIONALITY ("New functionality"),
    ERROR ("Error"),
    IMPROVEMENT ("Improvement"),
    ANALYTICS ("Analytics");

    private String Type;

    TaskType(String type) {
        Type = type;
    }

    public void setType(String type) {
        Type = type;
    }
}
