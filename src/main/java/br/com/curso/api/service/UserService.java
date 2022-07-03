package br.com.curso.api.service;

import br.com.curso.api.domain.Users;

import java.util.List;

public interface UserService {
    Users findById(Integer id);
    List<Users> findAll();
}
