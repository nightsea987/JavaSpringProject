package controllers;

import dto.EmployeeDto;
import entities.Employee;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.EmployeeService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
@Tag(name="Employee controller")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PreAuthorize("hasRole('admin')")
    @GetMapping
    @Operation(summary = "Get all employees")
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.findAll();
    }

    @PostAuthorize("hasRole('user')")
    @GetMapping("/{id}")
    @Operation(summary = "Get employee by id")
    public EmployeeDto getEmployeeById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @PostAuthorize("hasRole('user')")
    @PostMapping
    @Operation(summary = "Create employee")
    public EmployeeDto createEmployee(@RequestBody Employee employee) {
        return employeeService.save(EmployeeDto.fromEmployee(employee));
    }

    @PostAuthorize("hasRole('user')")
    @PutMapping("/{id}")
    @Operation(summary = "Update employee")
    public EmployeeDto updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, EmployeeDto.fromEmployee(employee));
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete employee")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
    }
}
