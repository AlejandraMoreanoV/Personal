package com.example.demo.servicio;

import com.example.demo.modelo.Sede;
import com.example.demo.modelo.Usuario;
import com.example.demo.persistencia.EntitySede;
import com.example.demo.persistencia.EntityUsuario;
import com.example.demo.persistencia.IRepositorioSede;
import com.example.demo.persistencia.ISedeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ServicioSede implements IServicioSede, ISedeMapper {

    private final IRepositorioSede iRepositorioSede;

    @Override
    public boolean agregarSede(Sede s) {
        EntitySede entitySede = toNewEntitySede(s);
        if (entitySede != null) {
            iRepositorioSede.save(entitySede);
            return true;
        }
        return false;
    }

    @Override
    public Sede buscarSedeId(int id) {
        Optional<EntitySede> entitySede = iRepositorioSede.findById(id);
        return toDtoSede(entitySede.get());
    }

    @Override
    public Sede buscarSedeCiudad(String ciudad) {
        Optional<EntitySede> entitySede = iRepositorioSede.findByCiudad(ciudad);
        return toDtoSede(entitySede.get());
    }

    @Override
    public boolean modificarSede(Sede s) {
        EntitySede entitySede = toEntitySede(s);
        if (entitySede != null) {
            iRepositorioSede.save(entitySede);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarSede(int id) {
        EntitySede entitySede = iRepositorioSede.findById(id).get();
        if (entitySede != null) {
            iRepositorioSede.delete(entitySede);
            return true;
        }
        return false;
    }

    @Override
    public List<Sede> listarSedes() {
        List<EntitySede> entityList = iRepositorioSede.findAll();
        return toListSedes(entityList);
    }

    @Override
    public Sede toDtoSede(EntitySede entitySede) {
        Sede sede = new Sede(entitySede.getId(), entitySede.getCiudad(),
                entitySede.getDireccion(), entitySede.getFechaRegistro(), entitySede.getM2(), toListUsuarios(entitySede.getListaUsuarios()));
        return sede;
    }

    @Override
    public EntitySede toNewEntitySede(Sede sede) {
        EntitySede entitySede = new EntitySede(sede.getId(), sede.getCiudad(), sede.getDireccion(),
                sede.getFechaRegistro(), sede.getM2(), null);
        return entitySede;
    }
    @Override
    public EntitySede toEntitySede(Sede sede) {
        EntitySede entitySede = new EntitySede(sede.getId(), sede.getCiudad(), sede.getDireccion(),
                sede.getFechaRegistro(), sede.getM2(), toListEntityUsuarios(sede.getId(), sede.getListaUsuarios()));
        return entitySede;
    }

    @Override
    public List<EntitySede> toListEntitySedes(List<Sede> listSedes) {
        List<EntitySede> listEntitySedes = new ArrayList<>();
        for (Sede sede : listSedes) {
            EntitySede entitySede = new EntitySede(sede.getId(), sede.getCiudad(), sede.getDireccion(),
                    sede.getFechaRegistro(), sede.getM2(), toListEntityUsuarios(sede.getId(), sede.getListaUsuarios()));
            listEntitySedes.add(entitySede);
        }
        return listEntitySedes;
    }

    public List<Sede> toListSedes(List<EntitySede> listEntitySedes) {
        List<Sede> listSedes = new ArrayList<>();
        for (EntitySede entitySede : listEntitySedes) {
            Sede sede = new Sede(entitySede.getId(), entitySede.getCiudad(), entitySede.getDireccion(),
                    entitySede.getFechaRegistro(), entitySede.getM2(), toListUsuarios(entitySede.getListaUsuarios()));
            listSedes.add(sede);
        }
        return listSedes;
    }

    @Override
    public List<EntityUsuario> toListEntityUsuarios(int idSede, List<Usuario> listUsuarios) {
        List<EntityUsuario> listEntityUsuarios = new ArrayList<>();
        for (Usuario usuario : listUsuarios) {
            EntityUsuario entityUsuario = new EntityUsuario(usuario.getId(), usuario.getNombre(), usuario.getApellido(),
                    usuario.getFechaInscripcion(), usuario.getMensualidad(), idSede, null);
            listEntityUsuarios.add(entityUsuario);
        }
        return listEntityUsuarios;
    }

    @Override
    public List<Usuario> toListUsuarios(List<EntityUsuario> listEntityUsuarios) {
        List<Usuario> listUsuarios = new ArrayList<>();
        for (EntityUsuario entityUsuario : listEntityUsuarios) {
            Usuario usuario = new Usuario(entityUsuario.getId(), entityUsuario.getNombre(),
                    entityUsuario.getApellido(), entityUsuario.getFechaInscripcion(), entityUsuario.getMensualidad());
            listUsuarios.add(usuario);
        }
        return listUsuarios;
    }
}