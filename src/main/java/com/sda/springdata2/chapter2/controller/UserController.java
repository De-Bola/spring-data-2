package com.sda.springdata2.chapter2.controller;

import com.sda.springdata2.chapter2.domain.User;
import com.sda.springdata2.chapter2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/signup")
    public String displaySignUpForm(User user/*, Model model*/) {
//        model.addAttribute("users", repository.findAll());
        return "add-user";
    }

    @GetMapping(value = "/edit/{id}")
    public String displayUpdateForm(@PathVariable Long id, Model model) {
        User user = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping(value = "/update/{id}")
    public String update(@PathVariable("id") Long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }
        repository.save(user);
        model.addAttribute("users", repository.findAll());
        return "index";
    }

    @PostMapping(value = "/adduser")
    public String add(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        repository.save(user);
        model.addAttribute("users", repository.findAll());
        return "index";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        User user = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));
        repository.delete(user);
        model.addAttribute("users", repository.findAll());
        return "index";
    }
}
