package com.adrosystems.todosimple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adrosystems.todosimple.models.Task;

@Repository 
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser_Id(Long id);

    // @Query(value = "select t from Task t where t.user.id = :id")
    // List<Task> findByUser_id(@Param("id") Long id);

    // @Query(value = "select * from tasks t where t.user_id = :id", nativeQuery = true)
    // List<Task> findByUser_id(@Param("id") Long id);
}