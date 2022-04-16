package tqs.covid.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import tqs.covid.cache.Cache;
import tqs.covid.model.CovidInfo;
import tqs.covid.model.LastSixMonths;
import tqs.covid.model.Request;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONException;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class CovidServiceTest {

    @Mock
    private Cache cache;

    @Mock
    private Request api;

    @InjectMocks
    private CovidService service;


    @Test
    void getWorldDataTest() throws IOException, InterruptedException {

        CovidInfo world_info = new CovidInfo("1", "World", "All", null, null, 31, 111, 2, 555, 3, 222, 6, 10000000, 42, 76.8, 34.2);

        Mockito.when( api.requestTo( Mockito.anyString() ) ).thenReturn( "["+ world_info.toString() +"]" );

        CovidInfo info = service.getWorldData();

        assertEquals(world_info.toString(), info.toString());
       
    }


    @Test
    void getCountryDataTest() throws IOException, InterruptedException {

        CovidInfo country_info = new CovidInfo("2", "Portugal", "Europe", "prt", "pt", 31, 111, 2, 555, 3, 222, 6, 10000000, 42, 76.8, 34.2);

        Mockito.when( api.requestTo( Mockito.anyString() ) ).thenReturn( "["+ country_info.toString() +"]" );

        String iso = country_info.getIso();
        String countryname = country_info.getCountry();

        CovidInfo info = service.getCountryData(iso, countryname);

        assertEquals(country_info.toString(), info.toString());
       
    }


    @Test
    void getTop10Test() throws IOException, InterruptedException {

        CovidInfo country_info = new CovidInfo("2", "Portugal", "Europe", "prt", "pt", 31, 111, 2, 555, 3, 222, 6, 10000000, 42, 76.8, 34.2);
        CovidInfo country_info1 = new CovidInfo("3", "Spain", "Europe", "esp", "esp", 31, 111, 2, 555, 3, 222, 6, 10000000, 42, 76.8, 34.2);

        Mockito.when( api.requestTo( Mockito.anyString() ) ).thenReturn( "["+ country_info.toString() +", "+ country_info1.toString() +"]" );

        List<CovidInfo> info = service.getTop10();

        assertEquals(country_info.toString(), info.get(0).toString());
        assertEquals(country_info1.toString(), info.get(1).toString());

        assertThat(info).hasSize(2).extracting(CovidInfo::getCountry).contains(country_info.getCountry(), country_info1.getCountry() );
    }


    @Test
    void getLastSixMonthsDataTest() throws IOException, InterruptedException, JSONException, ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  

        Date date = formatter.parse("2022-04-14");
        LastSixMonths last = new LastSixMonths("1", "prt", "Portugal", "Europe", date, 111L, 2L, 555L, 3L, 222L, 6L);
        
        Date date1 = formatter.parse("2022-04-15");
        LastSixMonths last1 = new LastSixMonths("2", "prt", "Portugal", "Europe", date1, 111L, 2L, 555L, 3L, 222L, 6L);

        String map = "[ { \"Country\" : \"Portugal\", \"ThreeLetterSymbol\" : \"prt\" } ]";
    
        Mockito.when( api.requestTo( Mockito.anyString() ) ).thenReturn( "["+ last.toString() +", "+ last1.toString() +"]" );
        Mockito.when( api.requestTo( "npm-covid-data/countries-name-ordered" )).thenReturn( map );

        List<LastSixMonths> info = service.getLastSixMonthsData(last.getSymbol());

        assertEquals(last.toString(), info.get(0).toString());
        assertEquals(last1.toString(), info.get(1).toString());

        assertThat(info).hasSize(2).extracting(LastSixMonths::getCountry).contains(last.getCountry(), last1.getCountry() );
    }


    @Test
    void getMapCountryISOTest() throws IOException, InterruptedException, JSONException, ParseException {

        String map = "[ { \"Country\" : \"Portugal\", \"ThreeLetterSymbol\" : \"prt\" } ]";
    
        Mockito.when( api.requestTo( Mockito.anyString() )).thenReturn( map );

        HashMap<String, String> info = service.getMapCountryISO();

        assertEquals("Portugal", info.get("prt"));
        assertEquals(1, info.size());
    }


    @Test
    void getCountryByISOTest() throws IOException, InterruptedException, JSONException, ParseException {

        String map = "[ { \"Country\" : \"Portugal\", \"ThreeLetterSymbol\" : \"prt\" } ]";
    
        Mockito.when( api.requestTo( Mockito.anyString() )).thenReturn( map );

        String info = service.getCountryByISO("prt");
        String info1 = service.getCountryByISO("iso");

        assertEquals("Portugal", info);
        assertEquals(null, info1);

    }


    @Test
    void getISObyNameTest() throws IOException, InterruptedException, JSONException, ParseException {

        String map = "[ { \"Country\" : \"Portugal\", \"ThreeLetterSymbol\" : \"prt\" } ]";
    
        Mockito.when( api.requestTo( Mockito.anyString() )).thenReturn( map );

        String info = service.getISObyName("Portugal");
        String info1 = service.getISObyName("Pooooooortugal");

        assertEquals("prt", info);
        assertEquals(null, info1);
    }


    @Test
    void getCacheStatsTest() throws IOException, InterruptedException, JSONException, ParseException {

        String cache_stats = "{ \"time_to_live\": 1000, \"requests\": 10, \"hits\": 6, \"misses\": 4 }";
    
        Mockito.when( cache.toString() ).thenReturn( cache_stats );

        String info = service.getCacheStats();

        assertEquals(cache_stats, info);
    }
    
}

