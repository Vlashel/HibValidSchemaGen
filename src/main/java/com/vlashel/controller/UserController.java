package com.vlashel.controller;

import com.vlashel.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.naming.Binding;
import javax.validation.Valid;

/**
 * @author Vladyslav Shelest
 * @version 1.0
 * @since 24.09.2014
 */
@Controller
public class UserController {

    @RequestMapping(value = "/add-user", method = RequestMethod.GET)
    public String getUserForm(Model model) {

        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "user-form";

    }

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("userDto") @Valid UserDto userDto,
                          BindingResult bindingResult,
                          Model model) {

        if (bindingResult.hasErrors()) {
            return "redirect:/user-form";
        }

        return "success";

    }



}
