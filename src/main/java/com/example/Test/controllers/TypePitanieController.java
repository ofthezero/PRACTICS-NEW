package com.example.Test.controllers;

import com.example.Test.models.Type_pitanie;

import com.example.Test.repositories.TypePitanieRepository;
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
@RequestMapping("/type_pitanie")
public class TypePitanieController {

    @Autowired
    private TypePitanieRepository typePitanieRepository;

    @GetMapping("/")
    public String indet(Model model)
    {
        Iterable<Type_pitanie> type_pitanie = typePitanieRepository.findAll();
        model.addAttribute("type_pitanie",type_pitanie);
        return "type_pitanie/indet";
    }

    @GetMapping("/adde")
    public String addView(Model model)
    {
        model.addAttribute("type_pitanie", new Type_pitanie());
        return "type_pitanie/adde-type_pitanie";
    }

    @PostMapping("/adde")
    public String adds(
            @ModelAttribute("type_pitanie") @Valid Type_pitanie type_pitanieOne,
            BindingResult bind,
            Model model) {
        if (bind.hasErrors())
            return "type_pitanie/adde-type_pitanie";

        typePitanieRepository.save(type_pitanieOne);
        return "redirect:/type_pitanie/";
    }
    @GetMapping("/seartype_pitanie")
    public String adds(
            @RequestParam("name") String name,
            Model model)
    {
        List<Type_pitanie> type_pitanieList = typePitanieRepository.findByNameContains(name);
        model.addAttribute("type_pitanie",type_pitanieList);

        return "type_pitanie/indet";
    }

    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Type_pitanie> type_pitanie = typePitanieRepository.findById(id);
        ArrayList<Type_pitanie> type_pitanieArrayList = new ArrayList<>();
        type_pitanie.ifPresent(type_pitanieArrayList::add);
        model.addAttribute("type_pitanie",type_pitanieArrayList);
        return "type_pitanie/info-type_pitanie";
    }
    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Type_pitanie type_pitanie = typePitanieRepository.findById(id).orElseThrow();
        typePitanieRepository.delete(type_pitanie);

        // trailersRepository.deleteById(id);
        return "redirect:/type_pitanie/";
    }
    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!typePitanieRepository.existsById(id)) {
            return "redirect:/type_pitanie/";
        }

        Optional<Type_pitanie> type_pitanie = typePitanieRepository.findById(id);
        ArrayList<Type_pitanie> type_pitanieArrayList = new ArrayList<>();
        type_pitanie.ifPresent(type_pitanieArrayList::add);
        model.addAttribute("type_pitanie", type_pitanieArrayList.get(0));
        return "type_pitanie/edit-type_pitanie";
    }
    @PostMapping("/edit/{id}")
    public String editType_pitanie(
            @PathVariable("id") Long id,
            @ModelAttribute("type_pitanie") @Valid Type_pitanie type_pitanieOne, BindingResult bind,
            Model model
    ) {

        if (!typePitanieRepository.existsById(id)) {
            return "redirect:/type_pitanie/";
        }
        if (bind.hasErrors())
            return "type_pitanie/edit-type_pitanie";

        type_pitanieOne.setId(id);

        typePitanieRepository.save(type_pitanieOne);
        return "redirect:/type_pitanie/";
    }


}
