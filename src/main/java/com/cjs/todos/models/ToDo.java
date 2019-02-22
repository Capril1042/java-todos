package com.cjs.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="todo")
public class ToDo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long todoid;

    @Column( name="description", nullable = false)
    private String description;

    private String datestarted;

    private int completed;

    @ManyToOne
    @JoinColumn(name="userid", nullable = false)
    @JsonIgnore
    private Users userid;

    public ToDo()
    {
    }

    public long getTodoid()
    {
        return todoid;
    }

    public void setTodoid(long todoid)
    {
        this.todoid = todoid;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDatestarted()
    {
        return datestarted;
    }

    public void setDatestarted(String datestarted)
    {
        this.datestarted = datestarted;
    }

    public int isCompleted()
    {
        return completed;
    }

    public void setCompleted(int completed)
    {
        this.completed = completed;
    }

    public Users getUserid()
    {
        return userid;
    }

    public void setUserid(Users userid)
    {
        this.userid = userid;
    }
}
