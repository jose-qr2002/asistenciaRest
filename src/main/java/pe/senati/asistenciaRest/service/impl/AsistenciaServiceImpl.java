/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.senati.asistenciaRest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.senati.asistenciaRest.entity.Asistencia;
import pe.senati.asistenciaRest.repository.AsistenciaRepository;
import pe.senati.asistenciaRest.service.AsistenciaService;

/**
 *
 * @author Yamilly Prado
 */
@Service
public class AsistenciaServiceImpl implements AsistenciaService{
    
    @Autowired
    private AsistenciaRepository asistenciaRepository;
    
    @Override
    public List<Asistencia> findAll(){
        return (List<Asistencia>) asistenciaRepository.findAll();
    }
    
    @Override
    public Asistencia findById(Integer id){
        return asistenciaRepository.findById(id).orElse(null);
    }
    
    @Override
    public Asistencia save(Asistencia asistencia){
        return asistenciaRepository.save(asistencia);
    }
    
    @Override
    public void deleteById(Integer id){
        asistenciaRepository.deleteById(id);
    }
    
}
