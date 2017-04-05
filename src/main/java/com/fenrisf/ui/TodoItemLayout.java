package com.fenrisf.ui;

import com.fenrisf.model.Todo;
import com.fenrisf.repository.TodoRepository;
import com.vaadin.data.Binder;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;


/**
 * Created by albertomartinez on 5/04/17.
 */
public class TodoItemLayout extends HorizontalLayout {
    private final CheckBox done;
    private final TextField text;
    private TodoRepository repo;
    private Todo todo;

    public TodoItemLayout(Todo todo, TodoRepository repo) {
        this.repo = repo;
        this.todo = todo;
        done = new CheckBox();
        text = new TextField();
        text.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);

        addComponents(done);
        addComponentsAndExpand(text);
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        Binder<Todo> binder = new Binder<>(Todo.class);
        binder.bindInstanceFields(this);
        binder.setBean(todo);
        addListeners();
    }

    private void addListeners(){
        done.addValueChangeListener(change->{
            updateDone(todo);
        });
        text.addBlurListener(blur->{
            updateText(todo);
        });
    }

    private void updateDone(Todo todo) {
        todo.setDone(done.getValue());
        repo.save(todo);
    }

    private void updateText(Todo todo) {
        todo.setText(text.getValue());
        repo.save(todo);
    }
}
