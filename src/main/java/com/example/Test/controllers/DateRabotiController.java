package com.example.Test.controllers;

import com.example.Test.models.DateRaboti;

import com.example.Test.repositories.DateRabotiRepository;
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
@RequestMapping("/dateRaboti")
public class DateRabotiController {

    @Autowired
    private DateRabotiRepository dateRabotiRepository;

    @GetMapping("/")
    public String indet(Model model)
    {
        Iterable<DateRaboti> dateRaboti = dateRabotiRepository.findAll();
        model.addAttribute("dateRaboti",dateRaboti);
        return "dateRaboti/indet";
    }

    @GetMapping("/adde")
    public String addView(Model model)
    {
        model.addAttribute("dateRaboti", new DateRaboti());
        return "dateRaboti/adde-dateRaboti";
    }

    @PostMapping("/adde")
    public String adds(
            @ModelAttribute("dateRaboti") @Valid DateRaboti dateRabotiOne,
            BindingResult bind,
            Model model) {
        if (bind.hasErrors())
            return "dateRaboti/adde-dateRaboti";

        dateRabotiRepository.save(dateRabotiOne);
        return "redirect:/dateRaboti/";
    }
    @GetMapping("/seardateRaboti")
    public String adds(
            @RequestParam("daysRaboti") String daysRaboti,
            Model model)
    {
        List<DateRaboti> dateRabotiList = dateRabotiRepository.findByDaysRabotiContains(daysRaboti);
        model.addAttribute("dateRaboti",dateRabotiList);

        return "dateRaboti/indet";
    }

    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<DateRaboti> dateRaboti = dateRabotiRepository.findById(id);
        ArrayList<DateRaboti> dateRabotiArrayList = new ArrayList<>();
        dateRaboti.ifPresent(dateRabotiArrayList::add);
        model.addAttribute("dateRaboti",dateRabotiArrayList);
        return "dateRaboti/info-dateRaboti";
    }
    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        DateRaboti dateRaboti = dateRabotiRepository.findById(id).orElseThrow();
        dateRabotiRepository.delete(dateRaboti);

        // trailersRepository.deleteById(id);
        return "redirect:/dateRaboti/";
    }
    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!dateRabotiRepository.existsById(id)) {
            return "redirect:/dateRaboti/";
        }

        Optional<DateRaboti> dateRaboti = dateRabotiRepository.findById(id);
        ArrayList<DateRaboti> dateRabotiArrayList = new ArrayList<>();
        dateRaboti.ifPresent(dateRabotiArrayList::add);
        model.addAttribute("dateRaboti", dateRabotiArrayList.get(0));
        return "dateRaboti/edit-dateRaboti";
    }
    @PostMapping("/edit/{id}")
    public String editDateRaboti(
            @PathVariable("id") Long id,
            @ModelAttribute("dateRaboti") @Valid DateRaboti dateRabotiOne, BindingResult bind,
            Model model
    ) {

        if (!dateRabotiRepository.existsById(id)) {
            return "redirect:/dateRaboti/";
        }
        if (bind.hasErrors())
            return "dateRaboti/edit-dateRaboti";

        dateRabotiOne.setId(id);

        dateRabotiRepository.save(dateRabotiOne);
        return "redirect:/dateRaboti/";
    }


}
