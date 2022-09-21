package com.example.Test.controllers;

import com.example.Test.models.Passportd;
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
@RequestMapping("/passportd")
public class PassportDanniyeController {

    @Autowired
    private PassportDanniyeRepository passportDanniyeRepository;

    @GetMapping("/")
    public String indet(Model model)
    {
        Iterable<Passportd> passportd = passportDanniyeRepository.findAll();
        model.addAttribute("passportd",passportd);
        return "passportd/indet";
    }

    @GetMapping("/adde")
    public String addView(Model model)
    {
        model.addAttribute("passportd", new Passportd());
        return "passportd/adde-passportd";
    }

    @PostMapping("/adde")
    public String adds(
            @ModelAttribute("passportd") @Valid Passportd passportdOne,
            BindingResult bind,
            Model model) {
        if (bind.hasErrors())
            return "passportd/adde-passportd";

        passportDanniyeRepository.save(passportdOne);
        return "redirect:/passportd/";
    }
    @GetMapping("/searpassportd")
    public String adds(
            @RequestParam("seriaPassporta") String seriaPassporta,
            Model model)
    {
        List<Passportd> passportdList = passportDanniyeRepository.findBySeriaPassporta(seriaPassporta);
        model.addAttribute("passportd",passportdList);

        return "passportd/indet";
    }

    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Passportd> passportd = passportDanniyeRepository.findById(id);
        ArrayList<Passportd> passportdArrayList = new ArrayList<>();
        passportd.ifPresent(passportdArrayList::add);
        model.addAttribute("passportd",passportdArrayList);
        return "passportd/info-passportd";
    }
    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Passportd passportd = passportDanniyeRepository.findById(id).orElseThrow();
        passportDanniyeRepository.delete(passportd);

       // trailersRepository.deleteById(id);
        return "redirect:/passportd/";
    }
    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!passportDanniyeRepository.existsById(id)) {
            return "redirect:/passportd/";
        }

        Optional<Passportd> passportd = passportDanniyeRepository.findById(id);
        ArrayList<Passportd> passportdArrayList = new ArrayList<>();
        passportd.ifPresent(passportdArrayList::add);
        model.addAttribute("passportd", passportdArrayList.get(0));
        return "passportd/edit-passportd";
    }
    @PostMapping("/edit/{id}")
    public String editPassportd(
            @PathVariable("id") Long id,
            @ModelAttribute("passportd") @Valid Passportd passportdOne, BindingResult bind,
            Model model
    ) {

        if (!passportDanniyeRepository.existsById(id)) {
            return "redirect:/passportd/";
        }
        if (bind.hasErrors())
            return "passportd/edit-passportd";

        passportdOne.setId(id);

        passportDanniyeRepository.save(passportdOne);
        return "redirect:/passportd/";
    }


}
