package com.example.Test.controllers;

import com.example.Test.models.RoleName;

import com.example.Test.repositories.RoleRepository;
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
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/")
    public String indet(Model model)
    {
        Iterable<RoleName> role = roleRepository.findAll();
        model.addAttribute("role",role);
        return "role/indet";
    }

    @GetMapping("/adde")
    public String addView(Model model)
    {
        model.addAttribute("role", new RoleName());
        return "role/adde-role";
    }

    @PostMapping("/adde")
    public String adds(
            @ModelAttribute("role") @Valid RoleName roleOne,
            BindingResult bind,
            Model model) {
        if (bind.hasErrors())
            return "role/adde-role";

        roleRepository.save(roleOne);
        return "redirect:/role/";
    }
    @GetMapping("/searrole")
    public String adds(
            @RequestParam("nameRole") String nameRole,
            Model model)
    {
        List<RoleName> roleList = roleRepository.findByNameRoleContains(nameRole);
        model.addAttribute("role",roleList);

        return "role/indet";
    }

    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<RoleName> role = roleRepository.findById(id);
        ArrayList<RoleName> roleArrayList = new ArrayList<>();
        role.ifPresent(roleArrayList::add);
        model.addAttribute("role",roleArrayList);
        return "role/info-role";
    }
    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        RoleName role = roleRepository.findById(id).orElseThrow();
        roleRepository.delete(role);

        // trailersRepository.deleteById(id);
        return "redirect:/role/";
    }
    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!roleRepository.existsById(id)) {
            return "redirect:/role/";
        }

        Optional<RoleName> role = roleRepository.findById(id);
        ArrayList<RoleName> roleArrayList = new ArrayList<>();
        role.ifPresent(roleArrayList::add);
        model.addAttribute("role", roleArrayList.get(0));
        return "role/edit-role";
    }
    @PostMapping("/edit/{id}")
    public String editRole(
            @PathVariable("id") Long id,
            @ModelAttribute("role") @Valid RoleName roleOne, BindingResult bind,
            Model model
    ) {

        if (!roleRepository.existsById(id)) {
            return "redirect:/role/";
        }
        if (bind.hasErrors())
            return "role/edit-role";

        roleOne.setId(id);

        roleRepository.save(roleOne);
        return "redirect:/role/";
    }


}
