package com.ashu.demo.controller;

import com.ashu.demo.model.Horoscope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HoroscopeController {

    @GetMapping("/{sunsign}")
    public @ResponseBody String showIndex(@PathVariable("sunsign") String sunsign){
        RestTemplate restTemplate = new RestTemplate();
       // String sunsign = request.getParameter("sunsign") ;
        Horoscope horoscope = restTemplate.getForObject("http://horoscope-api.herokuapp.com/horoscope/today/" + sunsign, Horoscope.class);

        return horoscope.getHoroscope();
    }
}
