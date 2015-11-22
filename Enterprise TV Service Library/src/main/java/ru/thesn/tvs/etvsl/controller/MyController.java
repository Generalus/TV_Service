package ru.thesn.tvs.etvsl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.thesn.tvs.etvsl.model.Response;
import ru.thesn.tvs.etvsl.service.TVPackageService;

@RestController
@RequestMapping("/")
public class MyController {

    @Autowired
    TVPackageService service;

    @RequestMapping(value = "{key}", method = RequestMethod.GET)

    public @ResponseBody Response getResponseInJSON(@PathVariable String key) {
        Response response = new Response();
        response.setMessage("OK");
        return response;
    }

}
