package com.example.demo.servicio;

import com.example.demo.modelo.Sede;

import java.util.List;

public interface IServicioSede {

    boolean verificarValidezSede(Sede s);
    boolean agregarSede (Sede s);
    Sede buscarSedeId (int id);
    Sede buscarSedeCiudad (String ciudad);
    boolean modificarSede (Sede s);
    boolean eliminarSede (int id);
    List<Sede> listarSedes ();

}