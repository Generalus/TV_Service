package ru.thesn.tvs.etvsl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.thesn.tvs.etvsl.model.Response;

@Controller
@RequestMapping("/")
public class MyController {

    @RequestMapping(value = "{key}", method = RequestMethod.GET)
    public @ResponseBody
    Response getResponseInJSON(@PathVariable String key) {
        Response response = new Response();

        return response;
    }

}
