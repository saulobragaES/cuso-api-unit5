package br.com.curso.api.resources;

import br.com.curso.api.domain.Users;
import br.com.curso.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserResources {

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<Users> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(service.findById(id));
    }
}
