package com.example.Test.controllers;


import com.example.Test.models.Role;
import com.example.Test.models.RoleName;
import com.example.Test.models.User;
import com.example.Test.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {

    @Autowired
    public CourceRepository courceRepository;
    @Autowired
    public RoleRepository roleRepository;
    @Autowired
    public DolznostRepository dolznostRepository;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping
    public String all_users(Model model)
    {
        model.addAttribute("users",
                userRepository.findAll());
        return "users/info-users";
    }

    @GetMapping("/edit/{id}")
    public String user_role(
            @PathVariable("id")
            Long id,
            Model model)
    {
        Optional<User> user_raw = userRepository.findById(id);
        ArrayList<User> userArrayList = new ArrayList<>();

        user_raw.ifPresent(userArrayList::add);

        model.addAttribute("one_user",userArrayList);
        model.addAttribute("roles", Role.values());
        return "users/edit-users";

            }

    @PostMapping
    public String edit_role
            (@RequestParam("userId") User user,
             @RequestParam("username") String username,
             @RequestParam("password") String password,
             @RequestParam(name = "roles[]",required = false)
             String[] roles, Model model)
    {
        user.setUsername(username);
        user.setPassword(password);
        user.getRoles().clear();
        if(roles!=null)
        {
            for (String role_name:
                    roles) {
                user.getRoles().add(Role.valueOf(role_name));
            }

            model.addAttribute(
                    "error",
                    "Такой пользователь уже существует");

                   }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/admin";
    }
}
