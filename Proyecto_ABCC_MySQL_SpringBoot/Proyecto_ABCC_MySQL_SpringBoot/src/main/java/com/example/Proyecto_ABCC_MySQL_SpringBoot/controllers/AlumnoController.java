package com.example.Proyecto_ABCC_MySQL_SpringBoot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Proyecto_ABCC_MySQL_SpringBoot.models.AlumnoModel;
import com.example.Proyecto_ABCC_MySQL_SpringBoot.services.AlumnoService;

@Controller
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    // Listar todos los alumnos
    @GetMapping("/")
    public String listaAlumnos(Model model) {
        model.addAttribute("alumnos", alumnoService.findAlumnos());
        return "index";
    }

    // Mostrar formulario para nuevo alumno
    @GetMapping("/nuevoAlumno")
    public String mostrarFormNuevoAlumno(Model model) {
        AlumnoModel alumno = new AlumnoModel();
        model.addAttribute("alumno", alumno);
        return "nuevo_alumno";
    }

    // Guardar alumno (nuevo o editado)
    @PostMapping("/guardarAlumno")
    public String guardarAlumno(@ModelAttribute("alumno") AlumnoModel alumno) {
        alumnoService.saveAlumno(alumno);
        return "redirect:/";
    }

    // Mostrar formulario para editar alumno
    @GetMapping("/editar/{id}")
    public String mostrarFormEditar(@PathVariable(value = "id") Integer id, Model model) {
        AlumnoModel alumno = alumnoService.getAlumnoById(id);
        model.addAttribute("alumno", alumno);
        return "editar_alumno";
    }

    // Eliminar alumno
    @GetMapping("/eliminar/{id}")
    public String eliminarAlumno(@PathVariable(value = "id") Integer id) {
        alumnoService.deleteAlumnoById(id);
        return "redirect:/";
    }
}