package com.example.demo.controlador;

import com.example.demo.modelo.Sede;
import com.example.demo.servicio.IServicioSede;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value="/sede")
public class ControladorSede {

    @Autowired
    IServicioSede iServicioSede;

    @PostMapping
    public ResponseEntity<Sede> agregarSede (@RequestBody Sede sede) {
        if (iServicioSede.agregarSede(sede)) {
            return ResponseEntity.status(HttpStatus.OK).body(sede);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sede);
    }

    @GetMapping (path = "/id/{id}")
    public ResponseEntity<Sede> buscarSedeId (@PathVariable int id) {
        if (iServicioSede.buscarSedeId(id) != null) {
            return ResponseEntity.status(HttpStatus.OK).body(iServicioSede.buscarSedeId(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(iServicioSede.buscarSedeId(id));
    }

    @GetMapping (path = "/ciudad/{ciudad}")
    public ResponseEntity<Sede> buscarSedeCiudad (@PathVariable String ciudad) {
        if (iServicioSede.buscarSedeCiudad(ciudad) != null) {
            return ResponseEntity.status(HttpStatus.OK).body(iServicioSede.buscarSedeCiudad(ciudad));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(iServicioSede.buscarSedeCiudad(ciudad));
    }

    @PutMapping
    public ResponseEntity<Sede> modificarSede (@RequestBody Sede sede) {
        if (iServicioSede.modificarSede(sede)) {
            return ResponseEntity.status(HttpStatus.OK).body(sede);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sede);
    }

    @DeleteMapping (path = "/{id}")
    public ResponseEntity<String> eliminarSede (@PathVariable int id) {
        if (iServicioSede.eliminarSede(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("Sede eliminada.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sede no eliminada.");
    }

    @GetMapping
    public ResponseEntity<List<Sede>> listarSedes () {
        return ResponseEntity.status(HttpStatus.OK).body(iServicioSede.listarSedes());
    }

}