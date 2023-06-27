package repositories;

import entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import entities.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByEmployee(Employee employee);
    Optional<Task> findById(Long id);
    void deleteById(long id);
    Task save(Task entity);
}