package services;

import dto.EmployeeDto;
import entities.Employee;
import entities.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import repositories.EmployeeRepository;
import repositories.TaskRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;

    public List<Task> getAllTasksById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return taskRepository.findAllByEmployee(employee);
    }

    public List<EmployeeDto> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeDto::fromEmployee).collect(Collectors.toList());
    }

    public EmployeeDto getById(Long id) {
        Employee employee = employeeRepository.getById(id);
        return EmployeeDto.fromEmployee(employee);
    }

    public EmployeeDto save(EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto.getId(), employeeDto.getName(), employeeDto.getBirthday());
        employee = employeeRepository.save(employee);
        return EmployeeDto.fromEmployee(employee);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee existingEmployee = employeeRepository.getById(id);
        existingEmployee.setName(employeeDto.getName());
        existingEmployee.setBirthday(employeeDto.getBirthday());
        existingEmployee = employeeRepository.save(existingEmployee);
        return EmployeeDto.fromEmployee(existingEmployee);
    }
}