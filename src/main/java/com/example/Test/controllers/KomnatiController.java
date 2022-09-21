package com.example.Test.controllers;

import com.example.Test.models.Gosti;
import com.example.Test.models.Hotel;
import com.example.Test.models.Komnati;
import com.example.Test.models.TypeKomnati;
import com.example.Test.repositories.GostiRepository;
import com.example.Test.repositories.HotelRepository;
import com.example.Test.repositories.KomnatiRepository;
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
@RequestMapping("/komnati")
public class KomnatiController {

    @Autowired
    private KomnatiRepository komnatiRepository;

    @Autowired
    public TypeKomnatiRepository typeKomnatiRepository;

    @Autowired
    public HotelRepository hotelRepository;

    @GetMapping("/")
    public String indet(Model model)
    {
        Iterable<Komnati> komnati = komnatiRepository.findAll();
        model.addAttribute("komnati",komnati);
        return "komnati/indet";
    }

    @GetMapping("/adde")
    public String addView(Model model)
    {
        model.addAttribute("komnati", new Komnati());
        model.addAttribute("typeKomnati", typeKomnatiRepository.findAll());
        model.addAttribute("hotel", hotelRepository.findAll());

        return "komnati/adde-komnati";
    }

    @PostMapping("/adde")
    public String adds(
            @ModelAttribute("komnati") @Valid Komnati komnatiOne,
            BindingResult bind,@RequestParam String nameT,
            Model model) {
        if (bind.hasErrors())
            return "komnati/adde-komnati";

        model.addAttribute("komnati",komnatiOne);

        komnatiOne.setTypeKomnati(typeKomnatiRepository.findByNameTContains(nameT).get(0));

        komnatiRepository.save(komnatiOne);
        return "redirect:/komnati/";
    }
    @GetMapping("/searkomnati")
    public String adds(
            @RequestParam("nomerKomnati") String nomerKomnati,
            Model model)
    {
        List<Komnati> komnatiList = komnatiRepository.findByNomerKomnatiContains(nomerKomnati);
        model.addAttribute("komnati",komnatiList);

        return "komnati/indet";
    }

    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Komnati> komnati = komnatiRepository.findById(id);
        ArrayList<Komnati> komnatiArrayList = new ArrayList<>();
        komnati.ifPresent(komnatiArrayList::add);
        model.addAttribute("komnati",komnatiArrayList);
        return "komnati/info-komnati";
    }
    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Komnati komnati = komnatiRepository.findById(id).orElseThrow();
        komnatiRepository.delete(komnati);

       // trailersRepository.deleteById(id);
        return "redirect:/komnati/";
    }
    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!komnatiRepository.existsById(id)) {
            return "redirect:/komnati/";
        }

        Optional<Komnati> komnati = komnatiRepository.findById(id);
        ArrayList<Komnati> komnatiArrayList = new ArrayList<>();
        komnati.ifPresent(komnatiArrayList::add);
        model.addAttribute("komnati", komnatiArrayList.get(0));

        model.addAttribute("typeKomnati", typeKomnatiRepository.findAll());
        model.addAttribute("hotel", hotelRepository.findAll());



        return "komnati/edit-komnati";
    }
    @PostMapping("/edit/{id}")
    public String editKomnati(
            @PathVariable("id") Long id,
            @ModelAttribute("komnati") @Valid Komnati komnatiOne,  @RequestParam String strana, BindingResult bind,
            Model model
    ) {

        if (!komnatiRepository.existsById(id)) {
            return "redirect:/komnati/";
        }
        if (bind.hasErrors())
            return "komnati/edit-komnati";

        List <Hotel> hotel = hotelRepository.findByStranaContains(strana);


        komnatiOne.setId(id);

        komnatiRepository.save(komnatiOne);
        return "redirect:/komnati/";
    }


}
