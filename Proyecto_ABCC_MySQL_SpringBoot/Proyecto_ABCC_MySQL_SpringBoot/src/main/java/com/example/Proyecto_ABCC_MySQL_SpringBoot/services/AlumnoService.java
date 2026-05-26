package com.example.Proyecto_ABCC_MySQL_SpringBoot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Proyecto_ABCC_MySQL_SpringBoot.models.AlumnoModel;
import com.example.Proyecto_ABCC_MySQL_SpringBoot.repositories.AlumnoRepository;

@Service
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public List<AlumnoModel> findAlumnos() {
        return alumnoRepository.findAll();
    }

    public void saveAlumno(AlumnoModel alumno) {
        this.alumnoRepository.save(alumno);
    }

    public AlumnoModel getAlumnoById(Integer id) {
        Optional<AlumnoModel> optional = alumnoRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Alumno no encontrado para el id: " + id);
        }
    }

    public void deleteAlumnoById(Integer id) {
        this.alumnoRepository.deleteById(id);
    }
}