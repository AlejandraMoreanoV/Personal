package com.example.demo.servicio;

import com.example.demo.modelo.Sede;
import com.example.demo.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
//public class SINServicioUsuario implements IServicioUsuario {
public class SINServicioUsuario {

    @Autowired
    IServicioSede iServicioSede;

    public Sede buscarSede (int idSede) {
        return iServicioSede.buscarSedeId(idSede);
    }

    //@Override
    public boolean verificarValidezUsuario(Usuario u) {
        return  u != null
                && u.getId() > 0
                && u.getNombre() != null && !u.getNombre().trim().isEmpty()
                && u.getApellido() != null && !u.getApellido().trim().isEmpty()
                && u.getMensualidad() > 0
                && u.getFechaInscripcion() != null;
    }

    //@Override
    public boolean agregarUsuario(int idSede, Usuario u) {
        Sede sede = buscarSede(idSede);
        if (sede != null && verificarValidezUsuario(u) && buscarUsuarioId(idSede, u.getId()) == null ) {
            sede.getListaUsuarios().add(u);
            return true;
        }
        return false;
    }


    //@Override
    public Usuario buscarUsuarioId(int idSede, int id) {
        Sede sede = buscarSede(idSede);
        if (sede != null) {
            for (Usuario u : sede.getListaUsuarios()) {
                if (u.getId() == id) {
                    return u;
                }
            }
        }
        return null;
    }

    //@Override
    public Usuario buscarUsuarioNombre (int idSede, String nombre) {
        Sede sede = buscarSede(idSede);
        if (sede != null && nombre != null && !nombre.trim().isEmpty()) {
            for (Usuario u : sede.getListaUsuarios()) {
                if (u.getNombre().equals(nombre)) {
                    return u;
                }
            }
        }
        return null;
    }

    //@Override
    public boolean modificarUsuario (int idSede, Usuario u) {
        Usuario usuario = buscarUsuarioId(idSede, u.getId());
        if (usuario != null && verificarValidezUsuario(u)) {
            usuario.setNombre(u.getNombre());
            usuario.setApellido(u.getApellido());
            usuario.setMensualidad(u.getMensualidad());
            usuario.setFechaInscripcion(u.getFechaInscripcion());
            return true;
        }
        return false;
    }

    //@Override
    public boolean eliminarUsuario(int idSede, int id) {
        Usuario usuario = buscarUsuarioId(idSede, id);
        if (usuario != null) {
            buscarSede(idSede).getListaUsuarios().remove(usuario);
            return true;
        }
        return false;
    }

    //@Override
    public List<Usuario> listarUsuarios(int idSede) {
        return buscarSede(idSede).getListaUsuarios();
    }
}