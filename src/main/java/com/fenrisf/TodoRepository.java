package com.fenrisf;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by albertomartinez on 5/04/17.
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Transactional
    void deleteByDone(boolean done);
}
