package br.com.curso.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
// Esse cara foi tirado pq não foi usado em nenhum lugar, por causa dele os testes
// não estava sendo coberto 100%
//@NoArgsConstructor
public class UsersDTO {

    private  Integer id;
    private  String nome;
    private  String email;

    // Não deixa gravar dois email iguais no banco
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private  String password;

}
