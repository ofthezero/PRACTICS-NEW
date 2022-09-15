package com.example.Test.controllers;


import com.example.Test.models.PasportOTO;
import com.example.Test.models.PersonOTO;
import com.example.Test.repositories.PasportOTORepository;
import com.example.Test.repositories.PersonOTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainControllerOTO {
    @Autowired
    private PasportOTORepository pasportOTORepository;
    @Autowired
    private PersonOTORepository personOTORepository;



    @GetMapping("/personOTO")
    public String Main(Model model){
        Iterable<PasportOTO> pasportOTO = pasportOTORepository.findAll();
        model.addAttribute("pasportOTO", pasportOTO);
        return "personOTO";
    }

    @PostMapping("/personOTO/add")
    public String blogPostAdd(@RequestParam String name, @RequestParam("number") Long id, Model model)
    {
        System.out.println(name);
        PasportOTO pasportOTO = pasportOTORepository.findById(id).orElseThrow();
        System.out.println(pasportOTO.getId());
        PersonOTO personOTO = new PersonOTO(name, pasportOTO);
        personOTORepository.save(personOTO);
        return "personOTO";
    }
}
