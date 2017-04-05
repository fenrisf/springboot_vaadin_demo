package com.fenrisf.ui;

import com.fenrisf.model.Todo;
import com.fenrisf.repository.TodoRepository;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by albertomartinez on 4/04/17.
 */
@SpringComponent
public class TodoLayout extends VerticalLayout{
    private TodoRepository todoRepository;
    @Autowired
    public TodoLayout(TodoRepository repository){
        this.todoRepository = repository;
    }

    @PostConstruct
    public void init (){
        update();
    }

    private void update() {
        setTodos(todoRepository.findAll());
    }

    private void setTodos(List<Todo> todos) {
        removeAllComponents();
        todos.forEach(todo -> addComponent(new TodoItemLayout(todo, todoRepository)));
    }

    public void add(Todo todo) {
        todoRepository.save(todo);
        update();
    }

    public void deleteCompleted() {
        todoRepository.deleteByDone(true);
        update();
    }
}
