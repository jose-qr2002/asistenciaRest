/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.senati.asistenciaRest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.senati.asistenciaRest.entity.Persona;
import pe.senati.asistenciaRest.repository.PersonaRepository;
import pe.senati.asistenciaRest.service.PersonaService;

/**
 *
 * @author Yamilly Prado
 */
@Service
public class PersonaServiceImpl implements PersonaService {
    
    @Autowired
    private PersonaRepository personaRepository;
    
    @Override
    public List<Persona> findAll(){
        return (List<Persona>) personaRepository.findAll();
    }
    
    @Override
    public Persona findById(Integer id){
        return personaRepository.findById(id).orElse(null);
    }
    
    @Override
    public Persona findByPersDni(String dni){
        return personaRepository.findByPersDni(dni);
    }
    
    @Override
    public Persona save(Persona persona){
        return personaRepository.save(persona);
    }
    
    @Override
    public void deleteById(Integer id){
        personaRepository.deleteById(id);
    }
    
}
