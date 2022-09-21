package com.example.Test.controllers;

import com.example.Test.models.Cource;

import com.example.Test.repositories.CourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cource")
public class CourceController {

    @Autowired
    private CourceRepository courceRepository;

    @GetMapping("/")
    public String indet(Model model)
    {
        Iterable<Cource> cource = courceRepository.findAll();
        model.addAttribute("cource",cource);
        return "cource/indet";
    }

    @GetMapping("/adde")
    public String addView(Model model)
    {
        model.addAttribute("cource", new Cource());
        return "cource/adde-cource";
    }

    @PostMapping("/adde")
    public String adds(
            @ModelAttribute("cource") @Valid Cource courceOne,
            BindingResult bind,
            Model model) {
        if (bind.hasErrors())
            return "cource/adde-cource";

        courceRepository.save(courceOne);
        return "redirect:/cource/";
    }
    @GetMapping("/searcource")
    public String adds(
            @RequestParam("nameCource") String nameCource,
            Model model)
    {
        List<Cource> courceList = courceRepository.findByNameCourceContains(nameCource);
        model.addAttribute("cource",courceList);

        return "cource/indet";
    }

    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Cource> cource = courceRepository.findById(id);
        ArrayList<Cource> courceArrayList = new ArrayList<>();
        cource.ifPresent(courceArrayList::add);
        model.addAttribute("cource",courceArrayList);
        return "cource/info-cource";
    }
    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Cource cource = courceRepository.findById(id).orElseThrow();
        courceRepository.delete(cource);


        return "redirect:/cource/";
    }
    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!courceRepository.existsById(id)) {
            return "redirect:/cource/";
        }

        Optional<Cource> cource = courceRepository.findById(id);
        ArrayList<Cource> courceArrayList = new ArrayList<>();
        cource.ifPresent(courceArrayList::add);
        model.addAttribute("cource", courceArrayList.get(0));
        return "cource/edit-cource";
    }
    @PostMapping("/edit/{id}")
    public String editCource(
            @PathVariable("id") Long id,
            @ModelAttribute("cource") @Valid Cource courceOne, BindingResult bind,
            Model model
    ) {

        if (!courceRepository.existsById(id)) {
            return "redirect:/cource/";
        }
        if (bind.hasErrors())
            return "cource/edit-cource";

        courceOne.setId(id);

        courceRepository.save(courceOne);
        return "redirect:/cource/";
    }


}
