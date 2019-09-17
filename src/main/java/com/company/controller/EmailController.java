package com.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmailController {

    @Autowired
    public JavaMailSender emailSender;

    @RequestMapping(value = "/sendEmail/{email}", method = RequestMethod.GET)
    public ModelAndView sendSimpleEmail(@PathVariable("email") String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("DealerStat | Registration");
        message.setText("You have been registered in our platform!");
        emailSender.send(message);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
