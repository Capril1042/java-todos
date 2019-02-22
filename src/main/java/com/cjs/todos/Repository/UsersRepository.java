package com.cjs.todos.Repository;

import com.cjs.todos.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long>
{
Optional<Users> findByName(String name);
}
