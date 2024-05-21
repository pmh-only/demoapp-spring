package com.example.demo.controller;

import com.example.demo.dto.UserCreateRequestDto;
import com.example.demo.dto.UserDeleteRequestDto;
import com.example.demo.dto.UserFormResponseDto;
import com.example.demo.dto.UserUpdateRequestDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String createUserForm (Model model) {

        int user_count = userService.countUser();
        List<User> users = userService.getUserList();

        UserFormResponseDto userFormResponseDto = UserFormResponseDto.builder()
                .users(users)
                .user_count(user_count)
                .build();

        model.addAttribute(userFormResponseDto);

        return "create_user_form";
    }

    @PostMapping("/create_user")
    public String createUser (Model model, UserCreateRequestDto body) {
        User user = User.builder()
                .age(body.getAge())
                .name(body.getName())
                .build();

        userService.addUser(user);
        model.addAttribute(user);
        model.addAttribute("operation", "created");

        return "user_form_finished";
    }

    @GetMapping("/update_user")
    public String updateUserForm (Model model, @RequestParam("id") UUID id) {
        User user = userService.findUser(id);
        if (user == null)
            return "redirect:/";

        model.addAttribute(user);

        return "update_user_form";
    }


    @PostMapping("/update_user")
    public String updateUser (Model model, UserUpdateRequestDto body) {
        User user = User.builder()
                .id(body.getId())
                .age(body.getAge())
                .name(body.getName())
                .build();

        userService.addUser(user);
        model.addAttribute(user);
        model.addAttribute("operation", "modified");

        return "user_form_finished";
    }

    @GetMapping("/delete_user")
    public String deleteUserForm (Model model, @RequestParam("id") UUID id) {
        User user = userService.findUser(id);
        if (user == null)
            return "redirect:/";

        model.addAttribute(user);

        return "delete_user_form";
    }


    @PostMapping("/delete_user")
    public String deleteUser (Model model, UserDeleteRequestDto body) {
        User user = userService.deleteUser(body.getId());

        model.addAttribute(user);
        model.addAttribute("operation", "deleted");

        return "user_form_finished";
    }
}
