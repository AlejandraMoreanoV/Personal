package com.example.demo.servicio;

public class SedeInvalidaException extends RuntimeException {
    public SedeInvalidaException(String mensaje) {
        super(mensaje);
    }
}