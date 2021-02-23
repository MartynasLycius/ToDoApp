package todo.proit.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import todo.proit.persistence.entity.Task;

/**
 * @author dipanjal
 * @since 2/18/2021
 */
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
    Task getDistinctById(long id);
}
