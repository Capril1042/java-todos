package com.cjs.todos.Repository;

import com.cjs.todos.models.ToDo;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long>
{
    @Query(value = "SELECT u.name as user, t.description, t.datestarted, t.completed FROM todo t, users u WHERE t.userid = u.userid", nativeQuery = true)
    List<ToDo> todoUsers();

    @Query(value = "SELECT * FROM todo WHERE completed = 0", nativeQuery = true)
    List<ToDo> todoCompletedFalse();
}
