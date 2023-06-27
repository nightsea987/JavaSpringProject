package services;

import dto.CommentDto;
import entities.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import repositories.CommentRepository;
import entities.Comment;
import repositories.TaskRepository;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    public List<CommentDto> getAllByTaskId(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        List<Comment> comments = commentRepository.findAllByTask(task);
        return comments.stream().map(CommentDto::fromComment).collect(Collectors.toList());
    }

    public List<CommentDto> findAll() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(CommentDto::fromComment).collect(Collectors.toList());
    }

    public CommentDto getById(Long id) {
        Comment comment = commentRepository.getById(id);
        return CommentDto.fromComment(comment);
    }

    public CommentDto save(CommentDto commentDto) {
        Comment comment = new Comment(commentDto.getId(), commentDto.getContent(), commentDto.getAuthor(),
                commentDto.getCreationDate(), taskRepository.getById(commentDto.getTaskId()));
        comment = commentRepository.save(comment);
        return CommentDto.fromComment(comment);
    }

    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    public CommentDto updateComment(Long id, CommentDto commentDto) {
        Comment existingComment = commentRepository.getById(id);
        existingComment.setContent(commentDto.getContent());
        existingComment.setTask(taskRepository.getById(commentDto.getTaskId()));
        existingComment.setAuthor(commentDto.getAuthor());
        existingComment.setCreationDate(commentDto.getCreationDate());
        existingComment = commentRepository.save(existingComment);
        return CommentDto.fromComment(existingComment);
    }
}