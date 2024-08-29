package com.proyecto.grupo_umg2024.model.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.proyecto.grupo_umg2024.model.entity.User;

public interface UserRepository extends CrudRepository<User,Long>{
    Optional<User> findByUsername(String username);
}
