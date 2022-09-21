package com.example.Test.controllers;

import com.example.Test.models.Address;
import com.example.Test.models.Bronirovanie;
import com.example.Test.models.Gosti;
import com.example.Test.models.Person;
import com.example.Test.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bron")
public class BronirovanieController {

    @Autowired
    public GostiRepository gostiRepository;
    @Autowired
    public TypePitanieRepository typePitanieRepository;
    @Autowired
    public KomnatiRepository komnatiRepository;
    @Autowired
    private BronirovanieRepository bronirovanieRepository;

    @GetMapping("/")
    public String indet(Model model)
    {
        Iterable<Bronirovanie> bronirovanie = bronirovanieRepository.findAll();
        model.addAttribute("bronirovanie",bronirovanie);
        return "bronirovanie/indet";
    }

    @GetMapping("/adde")
    public String addView(Model model)
    {
        model.addAttribute("bronirovanie", new Bronirovanie());
        model.addAttribute("gosti", gostiRepository.findAll());
        model.addAttribute("komnati", komnatiRepository.findAll());
        model.addAttribute("type_pitanie", typePitanieRepository.findAll());

        return "bronirovanie/adde-bronirovanie";
    }

    @PostMapping("/adde")
    public String adds(
            @ModelAttribute("bronirovanie") @Valid Bronirovanie bronirovanieOne,
            BindingResult bind,
            @RequestParam String familia, @RequestParam String nomerKomnati, @RequestParam String data, @RequestParam String name, Model model
    ) {
        if (bind.hasErrors())
            return "bronirovanie/adde-bronirovanie";

        model.addAttribute("bronirovanie",bronirovanieOne);

        bronirovanieOne.setGosti(gostiRepository.findByFamiliaContains(familia).get(0));
        bronirovanieOne.setKomnati(komnatiRepository.findByNomerKomnatiContains(nomerKomnati).get(0));
        bronirovanieOne.setType_pitanie(typePitanieRepository.findByNameContains(name).get(0));

        bronirovanieRepository.save(bronirovanieOne);
        return "redirect:/bron/";
    }
    @GetMapping("/searbronirovanie")
    public String adds(
            @RequestParam("data") String data,
            Model model)
    {
        List<Bronirovanie> bronirovanieList = bronirovanieRepository.findByDataContains(data);
        model.addAttribute("bronirovanie",bronirovanieList);

        return "bronirovanie/indet";
    }

    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Bronirovanie> bronirovanie = bronirovanieRepository.findById(id);
        ArrayList<Bronirovanie> bronirovanieArrayList = new ArrayList<>();
        bronirovanie.ifPresent(bronirovanieArrayList::add);
        model.addAttribute("bronirovanie",bronirovanieArrayList);
        return "bronirovanie/info-bronirovanie";
    }
    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Bronirovanie bronirovanie = bronirovanieRepository.findById(id).orElseThrow();
        bronirovanieRepository.delete(bronirovanie);

       // trailersRepository.deleteById(id);
        return "redirect:/bron/";
    }
    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!bronirovanieRepository.existsById(id)) {
            return "redirect:/bron/";
        }

        Optional<Bronirovanie> bronirovanie = bronirovanieRepository.findById(id);
        ArrayList<Bronirovanie> bronirovanieArrayList = new ArrayList<>();
        bronirovanie.ifPresent(bronirovanieArrayList::add);
        model.addAttribute("bronirovanie", bronirovanieArrayList.get(0));

        model.addAttribute("gosti", gostiRepository.findAll());
        model.addAttribute("komnati", komnatiRepository.findAll());
        model.addAttribute("type_pitanie", typePitanieRepository.findAll());
        return "bronirovanie/edit-bronirovanie";
    }
    @PostMapping("/edit/{id}")
    public String editBronirovanie(
            @PathVariable("id") Long id,
            @ModelAttribute("bronirovanie") @Valid Bronirovanie bronirovanieOne, @RequestParam String familia,BindingResult bind,
            Model model
    ) {

        if (!bronirovanieRepository.existsById(id)) {
            return "redirect:/bron/";
        }
        if (bind.hasErrors())
            return "bronirovanie/edit-bronirovanie";


        List <Gosti> zooFinded = gostiRepository.findByFamiliaContains(familia);
        bronirovanieOne.setGosti(zooFinded.get(0));
        bronirovanieOne.setId(id);

        bronirovanieRepository.save(bronirovanieOne);
        return "redirect:/bron/";
    }


}
