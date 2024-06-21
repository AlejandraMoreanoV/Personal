package com.example.demo.servicio;

import com.example.demo.modelo.Usuario;

import java.util.List;

public interface IServicioUsuario {

    boolean verificarValidezUsuario (Usuario u);
    boolean agregarUsuario (int idSede, Usuario u);
    Usuario buscarUsuarioId (int idSede, int id);
    Usuario buscarUsuarioNombre (int idSede, String nombre);
    boolean modificarUsuario (int idSede, Usuario u);
    boolean eliminarUsuario (int idSede, int id);
    List<Usuario> listarUsuarios (int idSede);

}
