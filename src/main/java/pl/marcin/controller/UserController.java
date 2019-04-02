package pl.marcin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.marcin.data.UserRepository;
import pl.marcin.model.User;


import java.util.ArrayList;
import java.util.List;


@Controller
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @GetMapping("/")
    public String getAll(Model model){
        model.addAttribute("newUser", new User());
        List<User> allUsers=new ArrayList<>();
        userRepository.findAll().forEach(x->allUsers.add(x));
        model.addAttribute("allUsers",allUsers);
        return "index";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User newUser){
        userRepository.save(newUser);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model){
        User user = userRepository.findById(id).get();
        model.addAttribute("editUser",user);
        return "user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute("editUser") User editUser, @PathVariable long id){
        userRepository.save(editUser);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id){
        userRepository.deleteById(id);
       return "redirect:/";
    }



}
