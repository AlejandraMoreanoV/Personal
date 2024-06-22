package com.example.demo.persistencia;

import com.example.demo.modelo.Usuario;

public interface IUsuarioMapper {
    Usuario toDtoUsuario (EntityUsuario entityUsuario);
    EntityUsuario toEntityUsuario (int idSede, Usuario usuario);
}