package com.example.demo.controlador;

import com.example.demo.modelo.Usuario;
import com.example.demo.servicio.IServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value="/usuario")
public class ControladorUsuario {

    @Autowired
    IServicioUsuario iServicioUsuario;

    @PostMapping (path = "/{idSede}")
    public ResponseEntity<Usuario> agregarUsuario (@PathVariable int idSede, @RequestBody Usuario usuario) {
        if (iServicioUsuario.agregarUsuario(idSede, usuario)) {
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(usuario);
    }

    @GetMapping (path = "/id/{idSede}/{id}")
    public ResponseEntity<Usuario> buscarUsuarioNombre (@PathVariable int idSede, @PathVariable int id) {
        if (iServicioUsuario.buscarUsuarioId(idSede, id) != null) {
            return ResponseEntity.status(HttpStatus.OK).body(iServicioUsuario.buscarUsuarioId(idSede, id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(iServicioUsuario.buscarUsuarioId(idSede, id));
    }

    @GetMapping (path = "/nombre/{idSede}/{nombre}")
    public ResponseEntity<Usuario> buscarSedeCiudad (@PathVariable int idSede, @PathVariable String nombre) {
        if (iServicioUsuario.buscarUsuarioNombre(idSede, nombre) != null) {
            return ResponseEntity.status(HttpStatus.OK).body(iServicioUsuario.buscarUsuarioNombre(idSede, nombre));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(iServicioUsuario.buscarUsuarioNombre(idSede, nombre));
    }

    @PutMapping (path = "/{idSede}")
    public ResponseEntity<Usuario> modificarUsuario (@PathVariable int idSede, @RequestBody Usuario usuario) {
        if (iServicioUsuario.modificarUsuario(idSede, usuario)) {
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usuario);
    }

    @DeleteMapping (path = "/{idSede}/{id}")
    public ResponseEntity<String> eliminarUsuario (@PathVariable int idSede, @PathVariable int id) {
        if (iServicioUsuario.eliminarUsuario(idSede, id)) {
            return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no eliminado.");
    }

    @GetMapping (path = "/{idSede}")
    public ResponseEntity<List<Usuario>> listarUsuarios (@PathVariable int idSede) {
        return ResponseEntity.status(HttpStatus.OK).body(iServicioUsuario.listarUsuarios(idSede));
    }

}