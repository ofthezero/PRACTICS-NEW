package com.example.Test.controllers;

import com.example.Test.models.Gosti;
import com.example.Test.repositories.GostiRepository;
import com.example.Test.repositories.KomnatiRepository;
import com.example.Test.repositories.PassportDanniyeRepository;
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
@RequestMapping("/gosti")
public class GostiController {

    @Autowired
    private GostiRepository gostiRepository;

    @Autowired
    public PassportDanniyeRepository passportDanniyeRepository;

    @GetMapping("/")
    public String indet(Model model)
    {
        Iterable<Gosti> gosti = gostiRepository.findAll();
        model.addAttribute("gosti",gosti);
        return "gosti/indet";
    }

    @GetMapping("/adde")
    public String addView(Model model)
    {
        model.addAttribute("gosti", new Gosti());
        model.addAttribute("passportd", passportDanniyeRepository.findAll());

        return "gosti/adde-gosti";
    }

    @PostMapping("/adde")
    public String adds(
            @ModelAttribute("gosti") @Valid Gosti gostiOne,
            BindingResult bind, @RequestParam String seriaPassporta,
            Model model) {
        if (bind.hasErrors())
            return "gosti/adde-gosti";

        model.addAttribute("gosti",gostiOne);

        gostiOne.setPassportd(passportDanniyeRepository.findBySeriaPassporta(seriaPassporta).get(0));

        gostiRepository.save(gostiOne);
        return "redirect:/gosti/";
    }
    @GetMapping("/seargosti")
    public String adds(
            @RequestParam("familia") String familia,
            Model model)
    {
        List<Gosti> gostiList = gostiRepository.findByFamiliaContains(familia);
        model.addAttribute("gosti",gostiList);

        return "gosti/indet";
    }

    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Gosti> gosti = gostiRepository.findById(id);
        ArrayList<Gosti> gostiArrayList = new ArrayList<>();
        gosti.ifPresent(gostiArrayList::add);
        model.addAttribute("gosti",gostiArrayList);
        return "gosti/info-gosti";
    }
    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Gosti gosti = gostiRepository.findById(id).orElseThrow();
        gostiRepository.delete(gosti);

       // trailersRepository.deleteById(id);
        return "redirect:/gosti/";
    }
    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!gostiRepository.existsById(id)) {
            return "redirect:/gosti/";
        }

        Optional<Gosti> gosti = gostiRepository.findById(id);
        ArrayList<Gosti> gostiArrayList = new ArrayList<>();
        gosti.ifPresent(gostiArrayList::add);
        model.addAttribute("gosti", gostiArrayList.get(0));

        model.addAttribute("passportd", passportDanniyeRepository.findAll());
        return "gosti/edit-gosti";
    }
    @PostMapping("/edit/{id}")
    public String editGosti(
            @PathVariable("id") Long id,
            @ModelAttribute("gosti") @Valid Gosti gostiOne, BindingResult bind,
            Model model
    ) {

        if (!gostiRepository.existsById(id)) {
            return "redirect:/gosti/";
        }
        if (bind.hasErrors())
            return "gosti/edit-gosti";

        gostiOne.setId(id);

        gostiRepository.save(gostiOne);
        return "redirect:/gosti/";
    }


}
