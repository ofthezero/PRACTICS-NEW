package com.example.Test.controllers;

import com.example.Test.models.Dolznost;

import com.example.Test.repositories.DolznostRepository;
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
@RequestMapping("/dolznost")
public class DolznostController {

    @Autowired
    private DolznostRepository dolznostRepository;

    @GetMapping("/")
    public String indet(Model model)
    {
        Iterable<Dolznost> dolznost = dolznostRepository.findAll();
        model.addAttribute("dolznost",dolznost);
        return "dolznost/indet";
    }

    @GetMapping("/adde")
    public String addView(Model model)
    {
        model.addAttribute("dolznost", new Dolznost());
        return "dolznost/adde-dolznost";
    }

    @PostMapping("/adde")
    public String adds(
            @ModelAttribute("dolznost") @Valid Dolznost dolznostOne,
            BindingResult bind,
            Model model) {
        if (bind.hasErrors())
            return "dolznost/adde-dolznost";

        dolznostRepository.save(dolznostOne);
        return "redirect:/dolznost/";
    }
    @GetMapping("/seardolznost")
    public String adds(
            @RequestParam("nameDolznosti") String nameDolznosti,
            Model model)
    {
        List<Dolznost> dolznostList = dolznostRepository.findByNameDolznostiContains(nameDolznosti);
        model.addAttribute("dolznost",dolznostList);

        return "dolznost/indet";
    }

    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Dolznost> dolznost = dolznostRepository.findById(id);
        ArrayList<Dolznost> dolznostArrayList = new ArrayList<>();
        dolznost.ifPresent(dolznostArrayList::add);
        model.addAttribute("dolznost",dolznostArrayList);
        return "dolznost/info-dolznost";
    }
    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Dolznost dolznost = dolznostRepository.findById(id).orElseThrow();
        dolznostRepository.delete(dolznost);

        // trailersRepository.deleteById(id);
        return "redirect:/dolznost/";
    }
    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!dolznostRepository.existsById(id)) {
            return "redirect:/dolznost/";
        }

        Optional<Dolznost> dolznost = dolznostRepository.findById(id);
        ArrayList<Dolznost> dolznostArrayList = new ArrayList<>();
        dolznost.ifPresent(dolznostArrayList::add);
        model.addAttribute("dolznost", dolznostArrayList.get(0));
        return "dolznost/edit-dolznost";
    }
    @PostMapping("/edit/{id}")
    public String editDolznost(
            @PathVariable("id") Long id,
            @ModelAttribute("dolznost") @Valid Dolznost dolznostOne, BindingResult bind,
            Model model
    ) {

        if (!dolznostRepository.existsById(id)) {
            return "redirect:/dolznost/";
        }
        if (bind.hasErrors())
            return "dolznost/edit-dolznost";

        dolznostOne.setId(id);

        dolznostRepository.save(dolznostOne);
        return "redirect:/dolznost/";
    }


}
