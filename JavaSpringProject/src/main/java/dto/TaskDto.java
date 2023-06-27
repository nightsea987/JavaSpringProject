package dto;

import entities.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String taskName;
    private Date date;
    private Integer description;
    private String type;
    private Long employeeId;
    private String author;

    public static TaskDto fromTask(Task task) {
        return new TaskDto(task.getId(), task.getTaskName(), task.getDate(), task.getDescription(), task.getType(),
                task.getEmployee().getId(), task.getAuthor());
    }
}