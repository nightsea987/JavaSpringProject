package services;

import dto.TaskDto;
import entities.Employee;
import entities.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import entities.Comment;
import repositories.CommentRepository;
import repositories.EmployeeRepository;
import repositories.TaskRepository;
import tools.TaskTypeEnumException;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;
    private final EmployeeRepository employeeRepository;

    public List<TaskDto> getAllByEmployeeId(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        List<Task> tasks = taskRepository.findAllByEmployee(employee);
        return tasks.stream().map(TaskDto::fromTask).collect(Collectors.toList());
    }

    public List<Comment> getAllCommentsByTaskId(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        return commentRepository.findAllByTask(task);
    }

    public List<TaskDto> findAll() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(TaskDto::fromTask).collect(Collectors.toList());
    }

    public TaskDto getById(Long id) {
        Task task = taskRepository.getById(id);
        return TaskDto.fromTask(task);
    }

    public Task save(TaskDto taskDto) throws TaskTypeEnumException {
        Employee employee = employeeRepository.getById(taskDto.getEmployeeId());
        Task task = new Task(taskDto.getId(), taskDto.getTaskName(), taskDto.getDate(), taskDto.getDescription(),
                taskDto.getType(), employee, taskDto.getAuthor());
        task = taskRepository.save(task);
        return task;
    }

    public TaskDto updateTask(Long id, TaskDto taskDto) {
        Task existingTask = taskRepository.getById(id);
        Employee employee = employeeRepository.getById(taskDto.getEmployeeId());
        existingTask.setTaskName(taskDto.getTaskName());
        existingTask.setDate(taskDto.getDate());
        existingTask.setDescription(taskDto.getDescription());
        existingTask.setType(taskDto.getType());
        existingTask.setAuthor(taskDto.getAuthor());
        existingTask.setEmployee(employee);
        existingTask = taskRepository.save(existingTask);
        return TaskDto.fromTask(existingTask);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}