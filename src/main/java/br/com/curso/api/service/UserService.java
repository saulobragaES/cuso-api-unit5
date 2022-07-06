package br.com.curso.api.service;

import br.com.curso.api.domain.Users;
import br.com.curso.api.domain.dto.UsersDTO;

import java.util.List;

public interface UserService {
    Users findById(Integer id);
    List<Users> findAll();
    Users create(UsersDTO obj);
}
