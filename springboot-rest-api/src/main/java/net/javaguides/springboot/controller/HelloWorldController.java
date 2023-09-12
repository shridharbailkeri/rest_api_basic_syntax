package net.javaguides.springboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@ResponseBody // to convert pojo to json -> to http response
// instead of above to annotations use
@RestController
public class HelloWorldController {

    // HTTP GET Request

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World!";
    }
}
