package com.example.Test.controllers;

import com.example.Test.models.News;
import com.example.Test.models.Trailers;

import com.example.Test.repositories.TrailersRepository;
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
@RequestMapping("/trailers")
public class TrailersController {

    @Autowired
    private TrailersRepository trailersRepository;

    @GetMapping("/")
    public String indet(Model model)
    {
        Iterable<Trailers> trailers = trailersRepository.findAll();
        model.addAttribute("trailers",trailers);
        return "trailers/indet";
    }

    @GetMapping("/adde")
    public String addView(Model model)
    {
        model.addAttribute("trailers", new Trailers());
        return "trailers/adde-trailers";
    }

    @PostMapping("/adde")
    public String adds(
            @ModelAttribute("trailers") @Valid Trailers trailersOne,
            BindingResult bind,
            Model model) {
        if (bind.hasErrors())
            return "trailers/adde-trailers";

        trailersRepository.save(trailersOne);
        return "redirect:/trailers/";
    }
    @GetMapping("/seartrailers")
    public String adds(
            @RequestParam("nameT") String nameT,
            Model model)
    {
        List<Trailers> trailersList = trailersRepository.findByNameTContains(nameT);
        model.addAttribute("trailers",trailersList);

        return "trailers/indet";
    }

    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Trailers> trailers = trailersRepository.findById(id);
        ArrayList<Trailers> trailersArrayList = new ArrayList<>();
        trailers.ifPresent(trailersArrayList::add);
        model.addAttribute("trailers",trailersArrayList);
        return "trailers/info-trailers";
    }
    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Trailers trailers = trailersRepository.findById(id).orElseThrow();
        trailersRepository.delete(trailers);

       // trailersRepository.deleteById(id);
        return "redirect:/trailers/";
    }
    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!trailersRepository.existsById(id)) {
            return "redirect:/trailers/";
        }

        Optional<Trailers> trailers = trailersRepository.findById(id);
        ArrayList<Trailers> trailersArrayList = new ArrayList<>();
        trailers.ifPresent(trailersArrayList::add);
        model.addAttribute("trailers", trailersArrayList.get(0));
        return "trailers/edit-trailers";
    }
    @PostMapping("/edit/{id}")
    public String editTrailers(
            @PathVariable("id") Long id,
            @ModelAttribute("trailers") @Valid Trailers trailersOne, BindingResult bind,
            Model model
    ) {

        if (!trailersRepository.existsById(id)) {
            return "redirect:/trailers/";
        }
        if (bind.hasErrors())
            return "trailers/edit-trailers";

        trailersOne.setId(id);

        trailersRepository.save(trailersOne);
        return "redirect:/trailers/";
    }


}
