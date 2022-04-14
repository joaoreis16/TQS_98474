package tqs.covid.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import tqs.covid.model.CovidInfo;
import tqs.covid.model.Request;

import java.io.IOException;
import org.json.JSONException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CovidServiceTest {

    @Mock
    private Request api;

    @InjectMocks
    private CovidService service;
    
    @BeforeEach
    public void start()  throws IOException, InterruptedException, JSONException {

        CovidInfo country_info = new CovidInfo("1", "Portugal", "Europe", "PRT", "pt", 31, 111, 2, 555, 3, 222, 6, 10000000, 42, 76.8, 34.2);

        String map = "[ { \"Country\" : \"Portugal\", \"ThreeLetterSymbol\" : \"prt\" } ]";

        Mockito.when( api.requestTo( "npm-covid-data/countries-name-ordered" )).thenReturn( map );
        Mockito.when( api.requestTo( Mockito.anyString()) ).thenReturn( "["+ country_info.toString() +"]" );

    }


    @Test
    void getWorldDataTest() throws IOException, InterruptedException {

        CovidInfo country_info = new CovidInfo("1", "Portugal", "Europe", "PRT", "pt", 31, 111, 2, 555, 3, 222, 6, 10000000, 42, 76.8, 34.2);

        CovidInfo info = service.getWorldData();

        assertEquals(country_info.toString(), info.toString());
       
    }


    // methods to test
    // 
    // getWorldData()
    // getCountryData(String iso_code, String country_name)
    // getTop10()
    // getLastSixMonthsData(String iso)
    // getMapCountryISO() 
    // getCountryByISO(String iso_code)
    // getISObyName(String countryname)
    // getCacheStats()
    
}

