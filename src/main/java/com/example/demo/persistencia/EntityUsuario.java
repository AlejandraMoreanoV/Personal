package com.example.demo.persistencia;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table (name = "Usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntityUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nombre;
    private String apellido;
    @Column(name = "fecha_inscripcion")
    private LocalDateTime fechaInscripcion;
    private Double mensualidad;

    @Column(name = "id_sede")
    private Integer idSede;
    @ManyToOne
    @JoinColumn(name = "id_sede", insertable = false, updatable = false)
    private EntitySede sede;

}