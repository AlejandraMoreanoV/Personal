package com.example.demo.persistencia;

import com.example.demo.modelo.Sede;
import com.example.demo.modelo.Usuario;

import java.util.List;

public interface ISedeMapper {
    Sede toDtoSede (EntitySede entitySede);
    EntitySede toNewEntitySede(Sede sede);
    EntitySede toEntitySede (Sede sede);
    //List<EntitySede> toListEntitySedes (List<Sede> listSedes);
    List<EntityUsuario> toListEntityUsuarios (int idSede, List<Usuario> listUsuarios);
    List<Usuario> toListUsuarios (List<EntityUsuario> listEntityUsuarios);
}