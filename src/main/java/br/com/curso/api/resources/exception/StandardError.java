package br.com.curso.api.resources.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
//@Setter não está sendo usado, então posso tirar para dar 100% cobertura ao teste
@AllArgsConstructor
public class StandardError {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;

}
