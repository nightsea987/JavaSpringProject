package controllers;

import dto.TaskDto;
import entities.Task;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.TaskService;
import tools.TaskTypeEnumException;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tasks")
@Tag(name="Task controller")
public class TaskController {
    private final TaskService taskService;

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(summary = "Get all tasks")
    @GetMapping
    public List<TaskDto> getAllTasks() {
        return taskService.findAll();
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/{id}")
    @Operation(summary = "Get task by id")
    public TaskDto getTaskById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping
    @Operation(summary = "Create task")
    public Task createTask(@RequestBody TaskDto task) throws TaskTypeEnumException {
        return taskService.save(task);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping("/{id}")
    @Operation(summary = "Update task by id")
    public TaskDto updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, TaskDto.fromTask(task));
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task by id")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
    }
}