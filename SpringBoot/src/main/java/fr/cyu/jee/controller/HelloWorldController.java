package fr.cyu.jee.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {
    @GetMapping("/hello")
    public String sayHello(Map<String, String> model){
        model.put("msg","Hello World");
        return "hello_world";
    }
}
