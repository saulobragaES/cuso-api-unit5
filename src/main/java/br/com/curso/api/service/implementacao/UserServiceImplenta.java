package br.com.curso.api.service.implementacao;

import br.com.curso.api.domain.Users;
import br.com.curso.api.repository.UserRepository;
import br.com.curso.api.service.UserService;
import br.com.curso.api.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplenta implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrato..."));
    }

    public List<Users> findAll(){
        return repository.findAll();
    }

}
