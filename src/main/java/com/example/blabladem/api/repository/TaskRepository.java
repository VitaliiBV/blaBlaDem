package com.example.blabladem.api.repository;

import com.example.blabladem.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
    Page<Task> findAllByAssignee_Department_Id(Long departmentId, Pageable pageable);
}
