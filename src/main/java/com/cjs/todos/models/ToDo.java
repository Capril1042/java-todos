package com.cjs.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="todo")
public class ToDo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long todoid;

    @Column(nullable = false)
    private String description;

    private  Date datestarted;

    private boolean completed;

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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getDatestarted()
    {
        return datestarted;
    }

    public void setDatestarted(Date datestarted)
    {
        this.datestarted = datestarted;
    }

    public boolean isCompleted()
    {
        return completed;
    }

    public void setCompleted(boolean completed)
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
