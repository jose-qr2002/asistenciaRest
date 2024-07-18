/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.senati.asistenciaRest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.senati.asistenciaRest.entity.Persona;

/**
 *
 * @author Yamilly Prado
 */

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Integer> {
    Persona findByPersDni(String dni);
    
}
