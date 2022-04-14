package tqs.covid.service;

import org.springframework.stereotype.Service;

import tqs.covid.cache.Cache;
import tqs.covid.model.CovidInfo;
import tqs.covid.model.LastSixMonths;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CovidService {

    private static final Logger log = LoggerFactory.getLogger(CovidService.class);

    private static final String API_URL = "https://vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com/api/";
    private static final String HOST =  "vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com";
    private static final String KEY =  "fb44e7ccbcmshc1d51179e45f1c7p12742cjsnfacd51188b90";

    private HashMap<String, String> map_isoCode;
    private Cache cache;


    public CovidService() throws IOException, InterruptedException  {
        // change the time to live here (add as argument in the constructor)
        this.cache = new Cache();
        this.map_isoCode = this.getMapCountryISO();
    }


    ////////////////////////////////////////////////// API REQUESTS //////////////////////////////////////////////////

    public CovidInfo getWorldData() throws IOException, InterruptedException {

        String cacheKey = "world";

        Object world = cache.get( cacheKey );
        if (world != null) {
            log.info(">> [CACHE] Getting world data.");
            return (CovidInfo) world;
        }

        log.info(">> Getting world data.");

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(API_URL + "npm-covid-data/world"))
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
        Double test_Percentage = Double.parseDouble( data.get("Test_Percentage").toString() );
        Double infection_Risk = Double.parseDouble( data.get("Infection_Risk").toString() );

        CovidInfo world_data = new CovidInfo(id, country, continent, iso, TwoLetterSymbol, rank, total_cases, new_cases, total_deaths, new_deaths, total_recovered, new_recovered, population, serious_critical, test_Percentage, infection_Risk);

        cache.add(cacheKey, world_data);
        log.info("[CACHE SAVE]");  

        return world_data;
    }


    public CovidInfo getCountryData(String iso_code, String country_name) throws IOException, InterruptedException {

        String cacheKey = iso_code +"_"+ country_name;

        Object country_data = cache.get( cacheKey );
        if (country_data != null) {
            log.info(">> [CACHE] Getting {} data.", country_name );
            return (CovidInfo) country_data;
        }

        log.info(">> Getting {} covid-19 data.", country_name);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(API_URL + "npm-covid-data/country-report-iso-based/" + country_name + "/" + iso_code))
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
        Double test_Percentage = Double.parseDouble( data.get("Test_Percentage").toString() );
        Double infection_Risk = Double.parseDouble( data.get("Infection_Risk").toString() );

        CovidInfo new_country = new CovidInfo(id, country, continent, iso, TwoLetterSymbol, rank, total_cases, new_cases, total_deaths, new_deaths, total_recovered, new_recovered, population, serious_critical, test_Percentage, infection_Risk); 

        cache.add(cacheKey, new_country);
        log.info("[CACHE SAVE]");

        return new_country;
    }



    public List<CovidInfo> getTop10() throws IOException, InterruptedException {

        String cacheKey = "top10";

        Object lst = cache.get( cacheKey );
        if (lst != null) {
            log.info(">> [CACHE] Getting Top10 countries data");
            return (List<CovidInfo>) lst;
        }

        log.info(">> Getting Top10 countries most affected by covid.");
        ArrayList<CovidInfo> lst_countries = new ArrayList<>();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(API_URL + "npm-covid-data/"))
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
            Double test_Percentage = Double.parseDouble( data.get("Test_Percentage").toString() );
            Double infection_Risk = Double.parseDouble( data.get("Infection_Risk").toString() );

            CovidInfo new_country = new CovidInfo(id, country, continent, iso, TwoLetterSymbol, rank, total_cases, new_cases, total_deaths, new_deaths, total_recovered, new_recovered, population, serious_critical, test_Percentage, infection_Risk);
                
            if (!continent.equals("All"))
                lst_countries.add( new_country );

            if (lst_countries.size() == 10) break;

        }

        cache.add(cacheKey, lst_countries);
        log.info("[CACHE SAVE]");
        
        return lst_countries;
    }


    public List<LastSixMonths> getLastSixMonthsData(String iso) throws IOException, InterruptedException, ParseException, JSONException {

        String cacheKey = iso;

        Object cache_data = cache.get( cacheKey );
        if (cache_data != null) {
            log.info(">> [CACHE] Getting {} last six month data.", this.map_isoCode.get(iso));
            return (List<LastSixMonths>) cache_data;
        }

        log.info(">> Getting {} covid-19 data is the last six months.", iso);
        ArrayList<LastSixMonths> lst = new ArrayList<>();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(API_URL + "covid-ovid-data/sixmonth/" + iso))
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
            String symbol = data.get("symbol").toString();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
            Date date = formatter.parse(  data.get("date").toString() );

            Long total_cases = Long.parseLong( data.get("total_cases").toString() );
            Long new_cases = Long.parseLong( data.get("new_cases").toString() );
            Long total_deaths = Long.parseLong( data.get("total_deaths").toString() );
            Long new_deaths = Long.parseLong( data.get("new_deaths").toString() );
            Long total_tests = Long.parseLong( data.get("total_tests").toString() );
            Long new_tests = Long.parseLong( data.get("new_tests").toString() );
            
            LastSixMonths new_stats = new LastSixMonths(id, symbol, country, continent, date, total_cases, new_cases, total_deaths, new_deaths, total_tests, new_tests);
            
            lst.add( new_stats );

        }

        cache.add(cacheKey, lst);
        log.info("[CACHE SAVE]");

        return lst;
    }

    public HashMap<String, String> getMapCountryISO() throws IOException, InterruptedException {

        // this request doesn't need to be saved in cache because he only occurs once

        HashMap<String,String> map = new HashMap<>();

        log.info(">> Creating HashMap< ISO, CountryName > ");

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(API_URL + "npm-covid-data/countries-name-ordered"))
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

            if (countryname.contains(" ")) countryname = countryname.replaceAll(" ", "%20");

            map.put(iso, countryname);
        }

        return map;
    }

    ////////////////////////////////////////////////// ANOTHER FUNCTIONS //////////////////////////////////////////////////


    public String getCountryByISO(String iso_code) {
        if (this.map_isoCode.containsKey(iso_code)){
            return this.map_isoCode.get(iso_code);
        } 
        return null;
    }

    public String getISObyName(String countryname) {

        HashMap<String, String> map_countryname = new HashMap<>();
        for(HashMap.Entry<String, String> entry : this.map_isoCode.entrySet()){
            map_countryname.put(entry.getValue().toLowerCase(), entry.getKey());
        }

        if (map_countryname.containsKey(countryname)) {
            return map_countryname.get(countryname);
        } 
        return null; 
    }


    public String getCacheStats() {
        return cache.toString();
    }

    
}
