package dto;
import entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    private String name;
    private Integer birthday;

    public static EmployeeDto fromEmployee(Employee employee) {
        return new EmployeeDto(employee.getId(), employee.getName(), employee.getBirthday());
    }
}