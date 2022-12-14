package br.com.curso.api.resources;

import br.com.curso.api.domain.Users;
import br.com.curso.api.domain.dto.UsersDTO;
import br.com.curso.api.service.implementacao.UserServiceImplenta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserResourcesTest {

    public static final Integer ID      = 1;
    public static final String NOME     = "Saulo";
    public static final String EMAIL    = "saulobraga_es@hotmail.com";
    public static final String PASSWORD = "123";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado...";
    public static final int INDEX = 0;
    public static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema.";

    private Users users = new Users();
    private UsersDTO usersDTO = new UsersDTO();


    @InjectMocks
    private UserResources resources;

    @Mock
    private UserServiceImplenta service;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void quandoBuscaPeloIdEntaoRetornaSucesso() {
        // Mocando
        when(service.findById(anyInt())).thenReturn(users);
        when(mapper.map(any() , any())).thenReturn(usersDTO);

        ResponseEntity<UsersDTO> response = resources.findById(ID);

        // Primeira validação, o response não pode ser nulo
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UsersDTO.class, response.getBody().getClass());

        assertEquals( ID, response.getBody().getId());
        assertEquals( NOME, response.getBody().getNome());
        assertEquals( EMAIL, response.getBody().getEmail());
        assertEquals( PASSWORD, response.getBody().getPassword());

    }

    @Test
    void whenFinAllThenReturnAListOfUsrDTO() {
        when(service.findAll()).thenReturn(List.of(users));
        when(mapper.map(any(), any())).thenReturn(usersDTO);

        ResponseEntity<List<UsersDTO>> response = resources.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        // se der erro aqui, ja quebra o teste e ai ja sabemos que não está OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UsersDTO.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NOME, response.getBody().get(INDEX).getNome());
        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
        assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());
    }

    @Test
    void whenCreateThenReturnCreated() {
        when(service.create(any())).thenReturn(users);

        ResponseEntity<UsersDTO> response = resources.create(usersDTO);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        // Validando o Headers
        assertNotNull(response.getHeaders().get("Location"));


    }

    @Test
    void whenUpdateThenReturnSucesso() {
        when(service.update(usersDTO)).thenReturn(users);
        when(mapper.map(any(), any())).thenReturn(usersDTO);

        ResponseEntity<UsersDTO> response = resources.update(ID, usersDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UsersDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NOME, response.getBody().getNome());
        assertEquals(EMAIL, response.getBody().getEmail());

    }

    @Test
    void whenDeleteThenReturnSucesso() {
        // Quando não tem retorno usa assim
        doNothing().when(service).delete((anyInt()));

        ResponseEntity<UsersDTO> response = resources.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        verify(service, times(1)).delete(anyInt());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }

    private void startUser() {
        users = new Users(ID, NOME, EMAIL, PASSWORD);
        usersDTO = new UsersDTO(ID, NOME, EMAIL, PASSWORD);
    }

}