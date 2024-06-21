package com.example.demo.servicio;

import com.example.demo.modelo.Sede;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioSede implements IServicioSede {

    private List<Sede> listaSedes = new ArrayList<>();

    @Override
    public boolean verificarValidezSede(Sede s) {
        return  s != null
                && s.getId() > 0
                && s.getCiudad() != null && !s.getCiudad().trim().isEmpty()
                && s.getDireccion() != null && !s.getDireccion().trim().isEmpty()
                && s.getFechaRegistro() != null
                && s.getM2() > 0;
    }

    @Override
    public boolean agregarSede(Sede s) {
        if ( ( verificarValidezSede(s) ) && ( buscarSedeId(s.getId()) == null ) ) {
            s.setListaUsuarios(new ArrayList<>());
            listaSedes.add(s);
            return true;
        }
        return false;
    }

    @Override
    public Sede buscarSedeId(int id) {
        for (Sede s : listaSedes) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    @Override
    public Sede buscarSedeCiudad(String ciudad) {
        if (ciudad != null && !ciudad.trim().isEmpty()) {
            for (Sede s : listaSedes) {
                if (s.getCiudad().equals(ciudad)) {
                    return s;
                }
            }
        }
        return null;
    }

    @Override
    public boolean modificarSede(Sede s) {
        Sede sede = buscarSedeId(s.getId());
        if (sede != null && verificarValidezSede(s)) {
            //sede.setCiudad(s.getCiudad());
            sede.setDireccion(s.getDireccion());
            sede.setM2(s.getM2());
            //sede.setFechaRegistro(s.getFechaRegistro());
            return sede.getDireccion().equalsIgnoreCase(s.getDireccion()) && sede.getM2() == s.getM2();
        }
        return false;
    }

    @Override
    public boolean eliminarSede(int id) {
        Sede sede = buscarSedeId(id);
        if (sede != null) {
            listaSedes.remove(sede);
            return true;
        }
        return false;
    }

    @Override
    public List<Sede> listarSedes() {
        return listaSedes;
    }

}