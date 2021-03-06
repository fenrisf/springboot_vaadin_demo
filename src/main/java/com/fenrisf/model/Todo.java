package com.fenrisf.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by albertomartinez on 4/04/17.
 */
@Entity
public class Todo {
    @Id
    @GeneratedValue
    private long id;
    private String text;
    private boolean done;

    public Todo(String text, boolean done) {
        this.text = text;
        this.done = done;
    }

    public Todo(String text) {
        this.text = text;
    }

    public Todo(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
