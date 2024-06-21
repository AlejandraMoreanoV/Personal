package com.example.demo.persistencia;

import com.example.demo.modelo.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "Sede")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntitySede {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String ciudad;
    private String direccion;
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
    private Double m2;

    @OneToMany(mappedBy = "sede", fetch = FetchType.LAZY)
    public List<EntityUsuario> listaUsuarios;

}