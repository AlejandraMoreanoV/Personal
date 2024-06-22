package com.example.demo.servicio;

import com.example.demo.modelo.Sede;
import com.example.demo.modelo.Usuario;
import com.example.demo.persistencia.EntitySede;
import com.example.demo.persistencia.EntityUsuario;
import com.example.demo.persistencia.IRepositorioUsuario;
import com.example.demo.persistencia.IUsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicioUsuario implements IServicioUsuario, IUsuarioMapper {

    private final IRepositorioUsuario iRepositorioUsuario;

    @Override
    public boolean agregarUsuario(int idSede, Usuario u) {
        EntityUsuario entityUsuario = toEntityUsuario(idSede, u);
        if (entityUsuario != null) {
            iRepositorioUsuario.save(entityUsuario);
            return true;
        }
        return false;
    }

    @Override
    public Usuario buscarUsuarioId(int idSede, int id) {
        Optional<EntityUsuario> entityUsuario = iRepositorioUsuario.findById(id);
        return toDtoUsuario(entityUsuario.get());
    }

    @Override
    public Usuario buscarUsuarioNombre (int idSede, String nombre) {
        Optional<EntityUsuario> entityUsuario = iRepositorioUsuario.findByName(idSede, nombre);
        return toDtoUsuario(entityUsuario.get());
    }

    @Override
    public boolean modificarUsuario (int idSede, Usuario u) {
        EntityUsuario entityUsuario = toEntityUsuario(idSede, u);
        if (entityUsuario != null) {
            iRepositorioUsuario.save(entityUsuario);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarUsuario(int idSede, int id) {
        EntityUsuario entityUsuario = iRepositorioUsuario.findById(id).get();
        if (entityUsuario != null) {
            iRepositorioUsuario.delete(entityUsuario);
            return true;
        }
        return false;
    }

    @Override
    public List<Usuario> listarUsuarios(int idSede) {
        List<EntityUsuario> entityList = iRepositorioUsuario.findAll();
        return toListUsuarios(entityList);
    }

    @Override
    public Usuario toDtoUsuario(EntityUsuario entityUsuario) {
        Usuario usuario = new Usuario(entityUsuario.getId(), entityUsuario.getNombre(),
                entityUsuario.getApellido(), entityUsuario.getFechaInscripcion(), entityUsuario.getMensualidad());
        return usuario;
    }

    @Override
    public EntityUsuario toEntityUsuario(int idSede, Usuario usuario) {
        EntityUsuario entityUsuario = new EntityUsuario(usuario.getId(), usuario.getNombre(),
                usuario.getApellido(), usuario.getFechaInscripcion(), usuario.getMensualidad(), idSede, null);
        return entityUsuario;
    }

    private List<Usuario> toListUsuarios(List<EntityUsuario> entityList) {
        List<Usuario> usuarioList = new ArrayList<>();
        for (EntityUsuario entityUsuario : entityList) {
            Usuario usuario = toDtoUsuario(entityUsuario);
            usuarioList.add(usuario);
        }
        return usuarioList;
    }

}