package br.com.curso.api.service.implementacao;

import br.com.curso.api.domain.Users;
import br.com.curso.api.domain.dto.UsersDTO;
import br.com.curso.api.repository.UserRepository;
import br.com.curso.api.service.exceptions.ObjectNotFoundException;
import org.h2.engine.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplentaTest {

    public static final Integer ID      = 1;
    public static final String NOME     = "Saulo";
    public static final String EMAIL    = "saulobraga_es@hotmail.com";
    public static final String PASSWORD = "123";
    @InjectMocks
    private UserServiceImplenta service;

    @Mock
    private UserRepository repository;

    //Mocar é colocar o InjectMocks
    @Mock
    private ModelMapper mapper;

    private Users users;
    private UsersDTO usersDTO;
    private Optional<Users> optionalUsers;

    // Daqui para cima tudo que tiver na classe tem que está aqui.

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void quandoBuscarIdEntaoRetornInstanciaUsuario() {
        when(repository.findById(anyInt())).thenReturn(optionalUsers);

        Users response = service.findById(ID);

        // assegurando que não será null
        Assertions.assertNotNull(response);
        Assertions.assertEquals(Users.class, response.getClass());

        // assegurando que o ID que estou passando são iguais, pode inserir mais atributos se quiser
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NOME, response.getNome());
    }

    @Test
    void quandoBuscarIdEntaoRetornUmObjetoNaoEncontradoException() {
        when(repository.findById(anyInt())).thenThrow( new ObjectNotFoundException("Objeto não encontrado..."));

        try {
            service.findById(ID);
        } catch (Exception ex ) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado...", ex.getMessage());
        }
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        users = new Users(ID, NOME, EMAIL, PASSWORD);
        usersDTO = new UsersDTO(ID, NOME, EMAIL, PASSWORD);
        optionalUsers = Optional.of(new Users(ID, NOME, EMAIL, PASSWORD));
    }
}