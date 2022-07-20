package br.com.curso.api.service.implementacao;

import br.com.curso.api.domain.Users;
import br.com.curso.api.domain.dto.UsersDTO;
import br.com.curso.api.repository.UserRepository;
import br.com.curso.api.service.exceptions.DataIntegratyViolationException;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplentaTest {

    public static final Integer ID      = 1;
    public static final String NOME     = "Saulo";
    public static final String EMAIL    = "saulobraga_es@hotmail.com";
    public static final String PASSWORD = "123";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado...";
    public static final int INDEX = 0;
    public static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema.";

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
        when(repository.findById(anyInt())).thenThrow( new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));

        try {
            service.findById(ID);
        } catch (Exception ex ) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void quandoBuscarTodosEntaoRetornListaUsuarios() {
        when(repository.findAll()).thenReturn(List.of(users));

        List<Users> response = service.findAll();

        assertNotNull(response);
        assertEquals(1,response.size());  // Aqui eu asseguro que vem na lista somente um usuário.
        assertEquals(Users.class, response.get(INDEX).getClass());
        assertEquals(ID,response.get(INDEX).getId());
        assertEquals(NOME,response.get(INDEX).getNome());
        assertEquals(EMAIL,response.get(INDEX).getEmail());
        assertEquals(PASSWORD,response.get(INDEX).getPassword());
    }

    @Test
    void quandoCrioEntaoRetornoSucesso() {
        // Mocando o save
        when(repository.save(any())).thenReturn(users);

        // Chamando metodo
        Users response = service.create(usersDTO);
        // Assegurando que o metodo não vai ser nulo
        assertNotNull(response);
        // Objetos são iguais
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());

    }

    @Test
    void quandoCrioEntaoRetornoViolacaoDadosIntegracao() {
        // Mocando o save
        when(repository.findByEmail(anyString())).thenReturn(optionalUsers);

        try {
            //Desabilita so para forçar erro
            //optionalUsers.get().setId(2);
            service.create(usersDTO);
        } catch ( Exception ex ) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, ex.getMessage());
        }

    }

    @Test
    void quandoAtualizoEntaoRetornoSucesso() {
        // Mocando o save
        when(repository.save(any())).thenReturn(users);

        // Chamando metodo
        Users response = service.update(usersDTO);
        // Assegurando que o metodo não vai ser nulo
        assertNotNull(response);
        // Objetos são iguais
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());

    }

    @Test
    void quandoAtualizoEntaoRetornoViolacaoDadosIntegracao() {
        // Mocando o save
        when(repository.findByEmail(anyString())).thenReturn(optionalUsers);

        try {
            //Desabilita so para forçar erro
            //optionalUsers.get().setId(2);
            service.update(usersDTO);
        } catch ( Exception ex ) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, ex.getMessage());
        }

    }

    @Test
    void deleteComSucesso() {
        when(repository.findById(anyInt())).thenReturn(optionalUsers);
        // Não faça nada quando parametro passado for inteiro
        doNothing().when(repository).deleteById(anyInt());
        service.delete(ID);
        // se for chamado mais de uma vez está errado o teste, igual ao parametro passado.
        verify(repository, times(1)).deleteById(anyInt());
    }

    @Test
    void deleteComObjetoNaoEncotradoException() {

        when(repository.findById(anyInt()))
                .thenThrow( new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));

        try{
            service.delete(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }

    }

    private void startUser() {
        users = new Users(ID, NOME, EMAIL, PASSWORD);
        usersDTO = new UsersDTO(ID, NOME, EMAIL, PASSWORD);
        optionalUsers = Optional.of(new Users(ID, NOME, EMAIL, PASSWORD));
    }
}