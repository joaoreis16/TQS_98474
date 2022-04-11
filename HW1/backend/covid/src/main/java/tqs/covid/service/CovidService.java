package tqs.covid.service;

import org.springframework.stereotype.Service;

import tqs.covid.model.CovidInfo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CovidService {

    private static final Logger log = LoggerFactory.getLogger(CovidService.class);

    private static final String API_URL = "https://vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com/api/npm-covid-data/";
    private static final String HOST =  "vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com";
    private static final String KEY =  "fb44e7ccbcmshc1d51179e45f1c7p12742cjsnfacd51188b90";

    private HashMap<String, String> map_isoCode;


    public CovidService() throws IOException, InterruptedException  {
        this.map_isoCode = this.getMapCountryISO();
    }


    ////////////////////////////////////////////////// API REQUESTS //////////////////////////////////////////////////

    public CovidInfo getWorldData() throws IOException, InterruptedException {

        log.info(">> Getting world data.");

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(API_URL + "world"))
            .header("X-RapidAPI-Host", HOST)
            .header("X-RapidAPI-Key", KEY)
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JSONArray data_array = new JSONArray(response.body());
        JSONObject data = (JSONObject) data_array.get(0);

        String id = data.get("id").toString();
        String country = data.get("Country").toString();
        String continent = data.get("Continent").toString();
        String TwoLetterSymbol = data.get("TwoLetterSymbol").toString();
        String iso = null;
        Long rank = Long.parseLong( data.get("rank").toString() );

        Long total_cases = Long.parseLong( data.get("TotalCases").toString() );
        Long new_cases = Long.parseLong( data.get("NewCases").toString() );
        Long total_deaths = Long.parseLong( data.get("TotalDeaths").toString() );
        Long new_deaths = Long.parseLong( data.get("NewDeaths").toString() );
        Long total_recovered = Long.parseLong( data.get("TotalRecovered").toString() );
        Long new_recovered = Long.parseLong( data.get("NewRecovered").toString() );
        Long population = Long.parseLong( data.get("Population").toString() );
        Long serious_critical = Long.parseLong( data.get("Serious_Critical").toString() );

        return new CovidInfo(id, country, continent, iso, TwoLetterSymbol, rank, total_cases, new_cases, total_deaths, new_deaths, total_recovered, new_recovered, population, serious_critical);
    }


    public CovidInfo getCountryData(String iso_code, String country_name) throws IOException, InterruptedException {

        log.info(">> Getting {} covid-19 data.", country_name);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(API_URL + "country-report-iso-based/" + country_name + "/" + iso_code))
            .header("X-RapidAPI-Host", HOST)
            .header("X-RapidAPI-Key", KEY)
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JSONArray data_array = new JSONArray(response.body());
        JSONObject data = (JSONObject) data_array.get(0);

        String id = data.get("id").toString();
        String country = data.get("Country").toString();
        String continent = data.get("Continent").toString();
        String iso = data.get("ThreeLetterSymbol").toString();
        String TwoLetterSymbol = data.get("TwoLetterSymbol").toString();
        Long rank = Long.parseLong( data.get("rank").toString() );

        Long total_cases = Long.parseLong( data.get("TotalCases").toString() );
        Long new_cases = Long.parseLong( data.get("NewCases").toString() );
        Long total_deaths = Long.parseLong( data.get("TotalDeaths").toString() );
        Long new_deaths = Long.parseLong( data.get("NewDeaths").toString() );
        Long total_recovered = Long.parseLong( data.get("TotalRecovered").toString() );
        Long new_recovered = Long.parseLong( data.get("NewRecovered").toString() );
        Long population = Long.parseLong( data.get("Population").toString() );
        Long serious_critical = Long.parseLong( data.get("Serious_Critical").toString() );

        return new CovidInfo(id, country, continent, iso, TwoLetterSymbol, rank, total_cases, new_cases, total_deaths, new_deaths, total_recovered, new_recovered, population, serious_critical);
    }



    public List<CovidInfo> getTop10() throws IOException, InterruptedException {

        log.info(">> Getting all information for every countries.");
        ArrayList<CovidInfo> lst_countries = new ArrayList<>();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(API_URL))
            .header("X-RapidAPI-Host", HOST)
            .header("X-RapidAPI-Key", KEY)
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JSONArray data_array = new JSONArray(response.body());


        for (Object c : data_array) {

            JSONObject data = (JSONObject) c;

            String id = data.get("id").toString();
            String country = data.get("Country").toString();
            String continent = data.get("Continent").toString();
            String iso = data.get("ThreeLetterSymbol").toString();
            String TwoLetterSymbol = data.get("TwoLetterSymbol").toString();

            Long rank = Long.parseLong( data.get("rank").toString() );
            Long total_cases = Long.parseLong( data.get("TotalCases").toString() );
            Long new_cases = Long.parseLong( data.get("NewCases").toString() );
            Long total_deaths = Long.parseLong( data.get("TotalDeaths").toString() );
            Long new_deaths = Long.parseLong( data.get("NewDeaths").toString() );
            Long total_recovered = Long.parseLong( data.get("TotalRecovered").toString() );
            Long new_recovered = Long.parseLong( data.get("NewRecovered").toString() );
            Long population = Long.parseLong( data.get("Population").toString() );
            Long serious_critical = Long.parseLong( data.get("Serious_Critical").toString() );
            
            CovidInfo new_country = new CovidInfo(id, country, continent, iso, TwoLetterSymbol, rank, total_cases, new_cases, total_deaths, new_deaths, total_recovered, new_recovered, population, serious_critical);
            
            if (!continent.equals("All"))
                lst_countries.add( new_country );

            if (lst_countries.size() == 10) break;

        }
        
        return lst_countries;
    }
    

    ////////////////////////////////////////////////// ANOTHER FUNCTIONS //////////////////////////////////////////////////


    public String getCountryByISO(String iso_code) {
        return this.map_isoCode.get(iso_code);
    }


    public HashMap<String, String> getMapCountryISO() throws IOException, InterruptedException {

        HashMap<String,String> map = new HashMap<>();

        log.info(">> Creating HashMap< IsoCode, CountryName> ");

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(API_URL + "countries-name-ordered"))
            .header("X-RapidAPI-Host", HOST)
            .header("X-RapidAPI-Key", KEY)
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JSONArray data = new JSONArray(response.body());

        for (Object o : data){
            JSONObject country = (JSONObject) o;

            String countryname = country.get("Country").toString();
            String iso = country.get("ThreeLetterSymbol").toString();

            map.put(iso, countryname);
        }

        return map;
    }

    
}
