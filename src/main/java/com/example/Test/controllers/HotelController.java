package com.example.Test.controllers;

import com.example.Test.models.Hotel;
import com.example.Test.repositories.HotelRepository;
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
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/")
    public String indet(Model model)
    {
        Iterable<Hotel> hotel = hotelRepository.findAll();
        model.addAttribute("hotel",hotel);
        return "hotel/indet";
    }

    @GetMapping("/adde")
    public String addView(Model model)
    {
        model.addAttribute("hotel", new Hotel());
        return "hotel/adde-hotel";
    }

    @PostMapping("/adde")
    public String adds(
            @ModelAttribute("hotel") @Valid Hotel hotelOne,
            BindingResult bind,
            Model model) {
        if (bind.hasErrors())
            return "hotel/adde-hotel";

        hotelRepository.save(hotelOne);
        return "redirect:/hotel/";
    }
    @GetMapping("/searhotel")
    public String adds(
            @RequestParam("strana") String strana,
            Model model)
    {
        List<Hotel> hotelList = hotelRepository.findByStranaContains(strana);
        model.addAttribute("hotel",hotelList);

        return "hotel/indet";
    }

    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        ArrayList<Hotel> hotelArrayList = new ArrayList<>();
        hotel.ifPresent(hotelArrayList::add);
        model.addAttribute("hotel",hotelArrayList);
        return "hotel/info-hotel";
    }
    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Hotel hotel = hotelRepository.findById(id).orElseThrow();
        hotelRepository.delete(hotel);

       // trailersRepository.deleteById(id);
        return "redirect:/hotel/";
    }
    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!hotelRepository.existsById(id)) {
            return "redirect:/hotel/";
        }

        Optional<Hotel> hotel = hotelRepository.findById(id);
        ArrayList<Hotel> hotelArrayList = new ArrayList<>();
        hotel.ifPresent(hotelArrayList::add);
        model.addAttribute("hotel", hotelArrayList.get(0));
        return "hotel/edit-hotel";
    }
    @PostMapping("/edit/{id}")
    public String editHotel(
            @PathVariable("id") Long id,
            @ModelAttribute("hotel") @Valid Hotel hotelOne, BindingResult bind,
            Model model
    ) {

        if (!hotelRepository.existsById(id)) {
            return "redirect:/hotel/";
        }
        if (bind.hasErrors())
            return "hotel/edit-hotel";

        hotelOne.setId(id);

        hotelRepository.save(hotelOne);
        return "redirect:/hotel/";
    }


}
