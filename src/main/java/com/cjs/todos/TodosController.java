package com.cjs.todos;

import com.cjs.todos.Repository.ToDoRepository;
import com.cjs.todos.Repository.UsersRepository;
import com.cjs.todos.models.ToDo;
import com.cjs.todos.models.Users;
import com.sun.tools.javac.comp.Todo;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class TodosController
{
    @Autowired
    ToDoRepository todorepos;

    @Autowired
    UsersRepository usersrepos;

    @GetMapping("/users") // returns all the users
    public List<Users> allUsers()
    {
        return usersrepos.findAll();
    }

    @GetMapping("/todos") // return all the todos
    public List<ToDo> allTodos()
    {
        return todorepos.findAll();
    }

    @GetMapping("/users/userid/{userid}") // return the user based off of the user id
    public Optional<Users> userById(@PathVariable long id)
    {
       return usersrepos.findById(id);
    }

    @GetMapping("/users/username/{username}") // return the user based off of the user name
    public Optional<Users> userByName(@PathVariable String name)
    {
        return usersrepos.findByName(name);
    }
   @GetMapping ("/todos/todoid/{todoid}") // return the todo based off of the todo id
    public Optional<ToDo> todoByID(@PathVariable long id)
   {
       return todorepos.findById(id);
   }

    @GetMapping ("/todos/users")//return a listing of the todos with its assigned user's name
    public List<ToDo> todoWithUsers()
    {
        return todorepos.todoUsers();
    }

    @GetMapping("/todos/active") //return a listing of the todos not yet completed.
    public List<ToDo> activeTodos()
    {
        return todorepos.todoCompletedFalse();
    }

    @PostMapping("/users") // adds a user
   public Users newUsers(@RequestBody Users user) throws URISyntaxException
    {
        return usersrepos.save(user);
    }

   @PostMapping("/users") // adds a user
    public ToDo newToDO(@RequestBody ToDo todo) throws URISyntaxException
{
    return todorepos.save(todo);
}

    @PutMapping("/users/userid/{userid}") //updates a user based on userid
   public Users changeUser(@RequestBody Users newuser, @PathVariable long id) throws URISyntaxException
    {
        Optional<Users> updateUser = usersrepos.findById(id);
        if (updateUser.isPresent())
        {
            newuser.setUserid(id);
            usersrepos.save(newuser);

            return newuser;
        }
        else
        {
            return null;
        }
    }

    @PutMapping("/todos/todoid/{todoid}")// updates a todo based on todoid
    public ToDo changeToDo(@RequestBody ToDo newtodo, @PathVariable long id) throws URISyntaxException
    {
        Optional<ToDo> updateToDo = todorepos.findById(id);
        if (updateToDo.isPresent())
        {
            newtodo.setTodoid(id);
            todorepos.save(newtodo);

            return newtodo;
        }
        else
        {
            return null;
        }
    }

    @DeleteMapping("/users/userid/{userid}") //Deletes a user based off of their userid and deletes all their associated todos
    public Users deleteUser(@PathVariable long id)
    {
        Optional<Users> foundUser = usersrepos.findById(id);
        if (foundUser.isPresent())
        {
            usersrepos.deleteById(id);
            return foundUser.get();
        }
        else
        {
            return null;
        }
    }


    @DeleteMapping("/todos/todoid/{todoid") // deletes a todo based off its todoid
    public ToDo deleteTodo(@PathVariable long id)
    {
        Optional<ToDo> foundToDo = todorepos.findById(id);
        if (foundToDo.isPresent())
        {
            todorepos.deleteById(id);
            return foundToDo.get();
        }
        else
        {
            return null;
        }
    }
}
