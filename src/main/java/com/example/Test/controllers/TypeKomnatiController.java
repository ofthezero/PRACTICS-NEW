package com.example.Test.controllers;

import com.example.Test.models.TypeKomnati;
import com.example.Test.repositories.TypeKomnatiRepository;
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
@RequestMapping("/typeKomnati")
public class TypeKomnatiController {

    @Autowired
    private TypeKomnatiRepository typeKomnatiRepository;

    @GetMapping("/")
    public String indet(Model model)
    {
        Iterable<TypeKomnati> typeKomnati = typeKomnatiRepository.findAll();
        model.addAttribute("typeKomnati",typeKomnati);
        return "typeKomnati/indet";
    }

    @GetMapping("/adde")
    public String addView(Model model)
    {
        model.addAttribute("typeKomnati", new TypeKomnati());
        return "typeKomnati/adde-typeKomnati";
    }

    @PostMapping("/adde")
    public String adds(
            @ModelAttribute("typeKomnati") @Valid TypeKomnati typeKomnatiOne,
            BindingResult bind,
            Model model) {
        if (bind.hasErrors())
            return "typeKomnati/adde-typeKomnati";

        typeKomnatiRepository.save(typeKomnatiOne);
        return "redirect:/typeKomnati/";
    }
    @GetMapping("/seartypeKomnati")
    public String adds(
            @RequestParam("nameT") String nameT,
            Model model)
    {
        List<TypeKomnati> typeKomnatiList = typeKomnatiRepository.findByNameTContains(nameT);
        model.addAttribute("typeKomnati",typeKomnatiList);

        return "typeKomnati/indet";
    }

    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<TypeKomnati> typeKomnati = typeKomnatiRepository.findById(id);
        ArrayList<TypeKomnati> typeKomnatiArrayList = new ArrayList<>();
        typeKomnati.ifPresent(typeKomnatiArrayList::add);
        model.addAttribute("typeKomnati",typeKomnatiArrayList);
        return "typeKomnati/info-typeKomnati";
    }
    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        TypeKomnati typeKomnati = typeKomnatiRepository.findById(id).orElseThrow();
        typeKomnatiRepository.delete(typeKomnati);

       // trailersRepository.deleteById(id);
        return "redirect:/typeKomnati/";
    }
    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!typeKomnatiRepository.existsById(id)) {
            return "redirect:/typeKomnati/";
        }

        Optional<TypeKomnati> typeKomnati = typeKomnatiRepository.findById(id);
        ArrayList<TypeKomnati> typeKomnatiArrayList = new ArrayList<>();
        typeKomnati.ifPresent(typeKomnatiArrayList::add);
        model.addAttribute("typeKomnati", typeKomnatiArrayList.get(0));
        return "typeKomnati/edit-typeKomnati";
    }
    @PostMapping("/edit/{id}")
    public String editTypeKomnati(
            @PathVariable("id") Long id,
            @ModelAttribute("typeKomnati") @Valid TypeKomnati typeKomnatiOne, BindingResult bind,
            Model model
    ) {

        if (!typeKomnatiRepository.existsById(id)) {
            return "redirect:/typeKomnati/";
        }
        if (bind.hasErrors())
            return "typeKomnati/edit-typeKomnati";

        typeKomnatiOne.setId(id);

        typeKomnatiRepository.save(typeKomnatiOne);
        return "redirect:/typeKomnati/";
    }


}
