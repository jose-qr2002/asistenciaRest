/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.senati.asistenciaRest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.senati.asistenciaRest.entity.Asistencia;
import pe.senati.asistenciaRest.service.AsistenciaService;

/**
 *
 * @author Yamilly Prado
 */
@RestController
@RequestMapping("api/asistencia")
@Api(value="Microservicio de gestion de asistencias", description = "Microservicio de Asistencia")
@CrossOrigin(origins = {"http://localhost:4200"})
public class AsistenciaController {
    
    @Autowired
    private AsistenciaService asistenciaService;
    
    @ApiOperation(value = "Lista de asistencias")
    @GetMapping
    public ResponseEntity<?> findAll() {
        HashMap<String, Object> result = new HashMap<> ();
        result.put("success", true);
        result.put("message", "Lista de personas");
        result.put("data", asistenciaService.findAll());
        //ESTADO, MENSAJE, LISTA DE ASISTENCIAS
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @ApiOperation(value = "Lista una asistencia por id")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        HashMap<String, Object> result = new HashMap<> ();
        Asistencia data = asistenciaService.findById(id);
        if (data == null){
            result.put("success", false);
            result.put("message", "No existe asistencia con id: " + id);
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        } 
        
        result.put("success", true);
        result.put("message", "Lista de Asistencias");
        result.put("data", data);
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @ApiOperation(value = "Crea una asistencia")
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Asistencia asistencia){
        HashMap<String, Object> result = new HashMap<> ();
        result.put("success", true);
        result.put("message", "Asistencia registrada correctamente");
        result.put("data", asistenciaService.save(asistencia));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @ApiOperation(value = "Modifica una asistencia")
    @PutMapping("/modificar")
    public ResponseEntity<?> update(@RequestBody Asistencia asistencia) {
        Asistencia data = asistenciaService.save(asistencia);
        HashMap<String, Object> result = new HashMap<>();
        if (data == null) {
            result.put("success", false);
            result.put("message", "No existe persona con id: " + asistencia.getAsisId());
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        try {
            asistenciaService.save(asistencia);
            result.put("success", true);
            result.put("message", "Registro Modificado Correctamente");
            result.put("data", data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Exception(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ApiOperation(value = "Elimina una asistencia por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        
        Asistencia data = asistenciaService.findById(id);
        HashMap<String, Object> result = new HashMap<>();
        if (data == null) {
            result.put("success", false);
            result.put("message", "No existe asistencia con id: " + id);
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        try {
            asistenciaService.deleteById(id);
            result.put("success", true);
            result.put("message", "Registro Eliminado Correctamente");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Exception(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
}
