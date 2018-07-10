package com.example.demo.service;

import com.example.demo.controller.TreeController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RestController
public class TreeService {
    @RequestMapping("/tree")
    public Response getGenerationalOrder(@RequestParam(value="levels", defaultValue="2") int levels)  {
        return Response.status(Response.Status.OK).entity(new TreeController().getResults(levels)).build();
    }
}
