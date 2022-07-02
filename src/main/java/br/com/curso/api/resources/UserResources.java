package br.com.curso.api.resources;

import br.com.curso.api.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserResources {

    @GetMapping("/{id}")
    public ResponseEntity<Users> findById(@PathVariable Integer id){
        System.out.println(ResponseEntity.ok().body(new Users( 1, "Saulo","saulo@hotmail.com", "123")));
        return ResponseEntity.ok().body(new Users( 1, "Saulo","saulo@hotmail.com", "123"));
    }
}
