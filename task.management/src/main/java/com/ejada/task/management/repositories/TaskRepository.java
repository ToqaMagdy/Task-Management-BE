package com.ejada.task.management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejada.task.management.entities.Task;
import com.ejada.task.management.enums.Priority;
import com.ejada.task.management.enums.Status;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    public List<Task> findByStatusAndPriority(Status status, Priority priority);

    public List<Task> findByStatus(Status status);

    public List<Task> findByPriority(Priority priority);

}
