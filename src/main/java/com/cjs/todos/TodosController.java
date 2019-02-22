package com.cjs.todos;

import com.cjs.todos.Repository.ToDoRepository;
import com.cjs.todos.Repository.UsersRepository;
import com.cjs.todos.models.ToDo;
import com.cjs.todos.models.Users;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Api(value= "To-Do Application", description = " A To-Do Application for JX Sprint Challenge")
@RestController
@RequestMapping(path = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class TodosController
{
    @Autowired
    ToDoRepository todorepos;

    @Autowired
    UsersRepository usersrepos;


    @ApiOperation(value = "returns all users", response = List.class)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Successfully retrieve list"),
                    @ApiResponse(code = 401, message = "Not authorized for this resource"),
                    @ApiResponse(code = 403, message = "Access to resource forbidden"),
                    @ApiResponse(code = 404, message = "Resource not found")
            })
    @GetMapping("/users") // returns all the users
    public List<Users> allUsers()
    {
        return usersrepos.findAll();
    }

    @ApiOperation(value = "returns all to-do's", response = List.class)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Successfully retrieve list"),
                    @ApiResponse(code = 401, message = "Not authorized for this resource"),
                    @ApiResponse(code = 403, message = "Access to resource forbidden"),
                    @ApiResponse(code = 404, message = "Resource not found")
            })
    @GetMapping("/todos")
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

    @ApiOperation(value = "return a listing of the to-do's with its assigned user's name", response = List.class)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Successfully retrieve list"),
                    @ApiResponse(code = 401, message = "Not authorized for this resource"),
                    @ApiResponse(code = 403, message = "Access to resource forbidden"),
                    @ApiResponse(code = 404, message = "Resource not found")
            })
    @GetMapping ("/todos/users")//return a listing of the todos with its assigned user's name
    public List<ToDo> todoWithUsers()
    {
        return todorepos.todoUsers();
    }

    @ApiOperation(value = "return a listing of the to-do's not yet completed.", response = List.class)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Successfully retrieve list"),
                    @ApiResponse(code = 401, message = "Not authorized for this resource"),
                    @ApiResponse(code = 403, message = "Access to resource forbidden"),
                    @ApiResponse(code = 404, message = "Resource not found")
            })
    @GetMapping("/todos/active")
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

    @ApiOperation(value = "updates a to-do based on to-do id")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Successfully retrieve list"),
                    @ApiResponse(code = 401, message = "Not authorized for this resource"),
                    @ApiResponse(code = 403, message = "Access to resource forbidden"),
                    @ApiResponse(code = 404, message = "Resource not found")
            })
    @PutMapping("/todos/todoid/{todoid}")
    public ToDo changeToDo(
            @ApiParam( value = "This is the id of th user you want to update", required = true)
            @RequestBody ToDo newtodo, @PathVariable long id) throws URISyntaxException
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

    @ApiOperation(value = "deletes a todo based off its to-do id")
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Successfully retrieve list"),
                    @ApiResponse(code = 401, message = "Not authorized for this resource"),
                    @ApiResponse(code = 403, message = "Access to resource forbidden"),
                    @ApiResponse(code = 404, message = "Resource not found")
            })
    @DeleteMapping("/todos/todoid/{todoid")
    public ToDo deleteTodo( @ApiParam(value = "This is the id of the to-do you want to delete", required =true)
            @PathVariable long id)
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
