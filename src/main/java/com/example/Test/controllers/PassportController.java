package com.example.Test.controllers;
import com.example.Test.models.News;
import com.example.Test.models.Passport;
import com.example.Test.repositories.PassportRepository;
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
@RequestMapping("/passport")
public class PassportController {

    @Autowired
    private PassportRepository passportRepository;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<Passport> passport = passportRepository.findAll();
        model.addAttribute("passport", passport);
        return "passport/indes";
    }

    @GetMapping("/adds")
    public String addView(Model model) {
        model.addAttribute("passport", new Passport());
        return "passport/adds-passport";
    }

    @PostMapping("/adds")
    public String adds(
            @ModelAttribute("passport") @Valid Passport passportOne,
            BindingResult bind,
            Model model) {
        if (bind.hasErrors())
            return "passport/adds-passport";

        passportRepository.save(passportOne);
        return "redirect:/passport/";
    }

    @GetMapping("/searpassport")
    public String adds(
            @RequestParam("name") String name,
            Model model) {
        List<Passport> passportList = passportRepository.findByName(name);
        model.addAttribute("passport", passportList);

        return "passport/indes";
    }

    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model) {
        Optional<Passport> passport = passportRepository.findById(id);
        ArrayList<Passport> passportArrayList = new ArrayList<>();
        passport.ifPresent(passportArrayList::add);
        model.addAttribute("passport", passportArrayList);
        return "passport/info-passport";
    }

    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    ) {
        Passport passport = passportRepository.findById(id).orElseThrow();
        passportRepository.delete(passport);

        //passportRepository.deleteById(id);
        return "redirect:/passport/";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    ) {
        if (!passportRepository.existsById(id)) {
            return "redirect:/passport/";
        }

        Optional<Passport> passport = passportRepository.findById(id);
        ArrayList<Passport> passportArrayList = new ArrayList<>();
        passport.ifPresent(passportArrayList::add);
        model.addAttribute("passport", passportArrayList.get(0));
        return "passport/edit-passport";
    }

    @PostMapping("/edit/{id}")
    public String editPassport(
            @PathVariable("id") Long id,
            @ModelAttribute("passport") @Valid Passport passportOne, BindingResult bind,
            Model model
    ) {

        if (!passportRepository.existsById(id)) {
            return "redirect:/passport/";
        }
        if (bind.hasErrors())
            return "passport/edit-passport";

        passportOne.setId(id);

        passportRepository.save(passportOne);
        return "redirect:/passport/";
    }
}
