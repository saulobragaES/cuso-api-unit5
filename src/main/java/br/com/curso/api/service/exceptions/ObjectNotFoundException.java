package br.com.curso.api.service.exceptions;

public class ObjectNotFoundException extends  RuntimeException{

    public ObjectNotFoundException(String message){
        super(message);
    }
}
