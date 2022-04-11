package tqs.covid.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tqs.covid.model.CovidInfo;
import tqs.covid.service.CovidService;


@CrossOrigin
@RestController
@RequestMapping("/info/")
public class CovidController {


    @Autowired
    private CovidService service;


    @GetMapping("world")
    public CovidInfo getWorldData() throws IOException, InterruptedException {
        return service.getWorldData();
    }


    @GetMapping("{iso}")
    public CovidInfo getCountryData(@PathVariable String iso) throws IOException, InterruptedException {
        String countryname = service.getCountryByISO(iso);
        return service.getCountryData(iso, countryname);
    }


    @GetMapping("eachcoutry")
    public List<CovidInfo> getCountriesData() throws IOException, InterruptedException {
        return service.getTop10();
    }
    
}
