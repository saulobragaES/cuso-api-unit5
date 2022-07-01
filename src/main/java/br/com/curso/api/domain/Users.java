package br.com.curso.api.domain;

import lombok.*;

import javax.persistence.*;


@Data // gera todos os metodos necessários, @ToString, @Getter e @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private  String nome;

    @Column(unique = true)
    private  String email;
    private  String password;

}
