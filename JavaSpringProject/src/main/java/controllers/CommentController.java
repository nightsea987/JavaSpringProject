package controllers;

import dto.CommentDto;
import entities.Comment;
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
import services.CommentService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/comments")
@Tag(name="Comments controller")
public class CommentController {
    private final CommentService commentService;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all comments")
    @GetMapping
    public List<CommentDto> getAllComments() {
        return commentService.findAll();
    }

    @PostAuthorize("hasRole('user')")
    @Operation(summary = "Get comments by id")
    @GetMapping("/{id}")
    public CommentDto getCommentById(@PathVariable Long id) {
        return commentService.getById(id);
    }

    @PostAuthorize("hasRole('user')")
    @Operation(summary = "Create comment")
    @PostMapping
    public CommentDto createComment(@RequestBody Comment comment) {
        return commentService.save(CommentDto.fromComment(comment));
    }

    @PostAuthorize("hasRole('user')")
    @Operation(summary = "Update comment")
    @PutMapping("/{id}")
    public CommentDto updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        return commentService.updateComment(id, CommentDto.fromComment(comment));
    }

    @PreAuthorize("hasRole('admin')")
    @Operation(summary = "Delete comment")
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteById(id);
    }
}