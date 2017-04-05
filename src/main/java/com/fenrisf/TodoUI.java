package com.fenrisf;

import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by albertomartinez on 4/04/17.
 */
@SpringUI
public class TodoUI extends UI {
    private VerticalLayout root;

    TodoLayout todoLayout;

    @Autowired
    public TodoUI(TodoLayout todoLayout){
        this.todoLayout = todoLayout;
    }
    @Override
    protected void init(VaadinRequest request){
        setUpLayout();
        addHeader();
        addFrom();
        addTodoList();
        addDeleteButon();
    }

    private void setUpLayout() {
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(root);
    }

    private void addHeader() {
        Label header = new Label("TODOs");
        header.setStyleName(ValoTheme.LABEL_H1);
        root.addComponent(header);
    }

    private void addFrom() {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setWidth("80%");
        TextField task = new TextField();
        Button addTask = new Button();
        addTask.setStyleName(ValoTheme.BUTTON_PRIMARY);
        addTask.setIcon(VaadinIcons.PLUS_CIRCLE);
        addTask.addClickListener(click->{
           todoLayout.add(new Todo(task.getValue()));
           task.clear();
           task.focus();
        });
        task.focus();
        addTask.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        formLayout.addComponentsAndExpand(task);
        formLayout.addComponent(addTask);
        root.addComponent(formLayout);

    }

    private void addTodoList() {
        todoLayout.setWidth("80%");
        root.addComponent(todoLayout);
    }

    private void addDeleteButon(){
        root.addComponent(new Button("Delete compelted", click-> {
            todoLayout.deleteCompleted();
        }));
    }
}
