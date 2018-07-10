package com.example.demo.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlgorithmService {

    @RequestMapping("/test")
    public String test(@RequestParam(value="test", defaultValue="Test") String test) {
        return test;
    }
}
