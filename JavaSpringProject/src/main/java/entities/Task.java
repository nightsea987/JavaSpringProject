package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tools.TaskTypeEnumException;
import enums.TaskType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Arrays;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String taskName;
    @Column(name = "deadline", nullable = false)
    private Date date;
    @Column(name = "description", nullable = false)
    private Integer description;
    @Column(name = "type", nullable = false)
    private TaskType type;
    @ManyToOne
    @JoinColumn(name = "EmployeeId", referencedColumnName = "id")
    private Employee employee;
    @Column(name = "author", nullable = false)
    private String author;

    public Task(long id, String taskName, Date date, int description, String type, Employee employee, String author) throws TaskTypeEnumException {
        this.id = id;
        this.taskName = taskName;
        this.date = date;
        this.description = description;
        this.employee = employee;
        this.author = author;
        if (validateType(type))
            this.type = TaskType.valueOf(type);
        else
            throw TaskTypeEnumException.valueWasNotFoundInEnum("The value " + type + " not included in the set of values");
    }

    public boolean validateType(String type) {
        return Arrays.stream(TaskType.values()).map(Enum::name).anyMatch(code -> code.equals(type));
    }

    public String getType() {
        return type.getType();
    }

    public void setType(String type) {
        this.type.setType(type);
    }
}