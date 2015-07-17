package cz.mikropsoft.springboot.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    @RequestMapping("/test")
    @Secured({"ROLE_USER"})
    public String hi() {
        return "Hi";
    }

}
