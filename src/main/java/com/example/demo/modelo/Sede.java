package com.example.demo.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class Sede {
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String ciudad;
    @Getter @Setter
    private String direccion;
    @Getter @Setter
    private LocalDateTime fechaRegistro;
    @Getter @Setter
    private double m2;
    @Getter @Setter
    private List<Usuario> listaUsuarios;
}