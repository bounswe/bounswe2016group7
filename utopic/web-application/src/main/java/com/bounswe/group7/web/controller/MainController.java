/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bounswe.group7.web.controller;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ugurbor
 */
@RestController
public class MainController {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public ModelAndView helloController(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView hello = new ModelAndView("hello");
        hello.addObject("time", new Date());
        hello.addObject("message", "Hello World!");
        return hello;
    }
    
    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
        ModelAndView index = new ModelAndView("index");
        return index;
    }
}
