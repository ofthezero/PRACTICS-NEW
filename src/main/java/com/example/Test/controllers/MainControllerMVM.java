package com.example.Test.controllers;

import com.example.Test.models.Student;
import com.example.Test.models.University;
import com.example.Test.repositories.StudentRepository;
import com.example.Test.repositories.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainControllerMVM {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UniversityRepository universityRepository;

    @GetMapping("/person")
    private String Main(Model model){
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        Iterable<University> universities = universityRepository.findAll();
        model.addAttribute("universities", universities);

        return "person";
    }

    @PostMapping("/person/add")
    public String blogPostAdd(@RequestParam String student, @RequestParam String universiti, Model model)
    {
        Student student2 = studentRepository.findByName(student);
        University university2 = universityRepository.findByName(universiti);
        student2.getUniversities().add(university2);
        university2.getStudents().add(student2);
        studentRepository.save(student2);
        universityRepository.save(university2);
        return "person";
    }
}
