package tqs.covid.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tqs.covid.model.CovidInfo;
import tqs.covid.model.LastSixMonths;
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
        if (countryname == null) return new CovidInfo();
        return service.getCountryData(iso, countryname);
    }


    @GetMapping("top10")
    public List<CovidInfo> getCountriesData() throws IOException, InterruptedException {
        return service.getTop10();
    }


    @GetMapping("last6months/{iso}")
    public List<LastSixMonths> getLastMonthsData(@PathVariable String iso) throws IOException, InterruptedException, ParseException, JSONException {
        return service.getLastSixMonthsData(iso);
    }

    @GetMapping("iso/{countryname}")
    public String getISObyName(@PathVariable String countryname) throws IOException, InterruptedException, ParseException, JSONException {
        String iso = service.getISObyName(countryname);
        if (iso == null) return null;
        return "{ \"iso\" : \""+ iso +"\"}";
    }
    
    
}
