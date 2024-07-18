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
import pe.senati.asistenciaRest.entity.Persona;
import pe.senati.asistenciaRest.service.PersonaService;

/**
 *
 * @author Yamilly Prado
 */
@RestController
@RequestMapping("api/persona")
@Api(value="Microservicio de gestion de personas", description = "Microservicio de Persona")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PersonaController {
    
    @Autowired
    private PersonaService personaService;
    
    @ApiOperation(value = "Lista de personas")
    @GetMapping
    public ResponseEntity<?> findAll() {
        HashMap<String, Object> result = new HashMap<> ();
        result.put("success", true);
        result.put("message", "Lista de personas");
        result.put("data", personaService.findAll());
        //ESTADO, MENSAJE, LISTA DE PERSONAS
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @ApiOperation(value = "Lista una persona por id")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        HashMap<String, Object> result = new HashMap<> ();
        Persona data = personaService.findById(id);
        if (data == null){
            result.put("success", false);
            result.put("message", "No existe persona con id: " + id);
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        } 
        
        result.put("success", true);
        result.put("message", "Lista de personas");
        result.put("data", data);
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @ApiOperation(value = "Lista una persona por dni")
    @GetMapping("/dni/{dni}")
    public ResponseEntity<?> findByPersDni(@PathVariable String dni){
        HashMap<String, Object> result = new HashMap<> ();
        Persona data = personaService.findByPersDni(dni);
        if (data == null){
            result.put("success", false);
            result.put("message", "No existe persona con dni: " + dni);
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        } 
        
        result.put("success", true);
        result.put("message", "Persona encontrada por dni");
        result.put("data", data);
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @ApiOperation(value = "Crea una persona")
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Persona persona){
        HashMap<String, Object> result = new HashMap<> ();
        result.put("success", true);
        result.put("message", "Persona registrada correctamente");
        result.put("data", personaService.save(persona));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @ApiOperation(value = "Modifica una persona")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Persona persona) {
        HashMap<String, Object> result = new HashMap<>();
        Persona data = personaService.findById(id);
        if (data == null) {
            result.put("success", false);
            result.put("message", "No existe persona con id: " + id);
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        try {
            persona.setPersId(id);
            personaService.save(persona);
            result.put("success", true);
            result.put("message", "Registro Modificado Correctamente");
            result.put("data", data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Exception(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ApiOperation(value = "Elimina una persona por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        
        Persona data = personaService.findById(id);
        HashMap<String, Object> result = new HashMap<>();
        if (data == null) {
            result.put("success", false);
            result.put("message", "No existe persona con id: " + id);
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        try {
            personaService.deleteById(id);
            result.put("success", true);
            result.put("message", "Registro Eliminado Correctamente");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Exception(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    
}
