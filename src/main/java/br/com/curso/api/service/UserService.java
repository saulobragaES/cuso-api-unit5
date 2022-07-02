package br.com.curso.api.service;

import br.com.curso.api.domain.Users;

public interface UserService {
    Users findById(Integer id);
}
