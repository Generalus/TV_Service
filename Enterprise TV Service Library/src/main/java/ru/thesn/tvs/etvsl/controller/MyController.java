package ru.thesn.tvs.etvsl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.thesn.tvs.etvsl.model.Response;
import ru.thesn.tvs.etvsl.service.TVPackageService;

@Controller
@RequestMapping("/")
public class MyController {

    @Autowired
    TVPackageService service;

    @RequestMapping(value = "{key}", method = RequestMethod.GET)
    @ResponseBody
    public Response getResponseInJSON(@PathVariable String key) {
        Response response = new Response();
        response.setMessage("OK");
        return response;
    }

}
