package tqs.covid.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import tqs.covid.model.CovidInfo;
import tqs.covid.model.LastSixMonths;
import tqs.covid.service.CovidService;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CovidController.class)
public class CovidControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private CovidService service;


    @Test
    void getWorldDataTest() throws Exception {

        CovidInfo country_info = new CovidInfo("1", "Portugal", "Europe", "PRT", "pt", 31, 111, 2, 555, 3, 222, 6, 10000000, 42, 76.8, 34.2);

        when( service.getWorldData() ).thenReturn( country_info );

        mvc.perform(
            get("/info/world").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.*", hasSize(16)))
            .andExpect(jsonPath("$.id", is(country_info.getId())))
            .andExpect(jsonPath("$.country", is(country_info.getCountry())))
            .andExpect(jsonPath("$.continent", is(country_info.getContinent()))
        );
            
        verify(service, times(1)).getWorldData();

    }


    @Test
    void getCountryData() throws Exception {

        CovidInfo country_info = new CovidInfo("1", "Portugal", "Europe", "prt", "pt", 31, 111, 2, 555, 3, 222, 6, 10000000, 42, 76.8, 34.2);

        when( service.getCountryData( Mockito.anyString(), Mockito.anyString() ) ).thenReturn( country_info );
        when( service.getCountryByISO( Mockito.anyString() ) ).thenReturn( "Portugal" );

        mvc.perform(
            get("/info/prt").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.*", hasSize(16)))
            .andExpect(jsonPath("$.id", is(country_info.getId())))
            .andExpect(jsonPath("$.country", is(country_info.getCountry())))
            .andExpect(jsonPath("$.continent", is(country_info.getContinent()))
        );
            
        verify(service, times(1)).getCountryData("prt", "Portugal" );

    }


    @Test
    void getCountriesData() throws Exception {

        CovidInfo country_info = new CovidInfo("1", "Portugal", "Europe", "prt", "pt", 31, 111, 2, 555, 3, 222, 6, 10000000, 42, 76.8, 34.2);
        CovidInfo country_info1 = new CovidInfo("2", "Spain", "Europe", "esp", "es", 15, 432, 64, 352, 6, 64, 2, 48000000, 67, 76.8, 34.2);

        List<CovidInfo> countries = Arrays.asList(country_info, country_info1);

        when( service.getTop10() ).thenReturn( countries );

        mvc.perform(
            get("/info/top10").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].id", is(country_info.getId())))
            .andExpect(jsonPath("$[0].country", is(country_info.getCountry())))
            .andExpect(jsonPath("$[0].continent", is(country_info.getContinent())))
            .andExpect(jsonPath("$[1].id", is(country_info1.getId())))
            .andExpect(jsonPath("$[1].country", is(country_info1.getCountry())))
            .andExpect(jsonPath("$[1].continent", is(country_info1.getContinent()))
        );
            
        verify(service, times(1)).getTop10( );

    }


    @Test
    void getLastMonthsData() throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        Date date = formatter.parse("2022-04-14");
        LastSixMonths info = new LastSixMonths("1", "prt", "Portugal", "Europe", date, 111L, 2L, 555L, 3L, 222L, 6L);
 
        Date date1 = formatter.parse("2022-04-15");
        LastSixMonths info1 = new LastSixMonths("1", "prt", "Portugal", "Europe", date1, 222L, 2L, 555L, 3L, 222L, 6L);

        List<LastSixMonths> last6months_info = Arrays.asList(info, info1);

        when( service.getLastSixMonthsData("prt") ).thenReturn( last6months_info );

        mvc.perform(
            get("/info/last6months/prt").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].id", is(info.getId())))
            .andExpect(jsonPath("$[0].country", is(info.getCountry())))
            .andExpect(jsonPath("$[0].continent", is(info.getContinent())))
            .andExpect(jsonPath("$[1].id", is(info1.getId())))
            .andExpect(jsonPath("$[1].country", is(info1.getCountry())))
            .andExpect(jsonPath("$[1].continent", is(info1.getContinent()))
        );
            
        verify(service, times(1)).getLastSixMonthsData( "prt" );

    }


    @Test
    void getISObyName() throws Exception {

        CovidInfo country_info = new CovidInfo("1", "Portugal", "Europe", "prt", "pt", 31, 111, 2, 555, 3, 222, 6, 10000000, 42, 76.8, 34.2);

        when( service.getISObyName("Portugal") ).thenReturn( "prt" );

        mvc.perform(
            get("/info/iso/Portugal").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.*", hasSize(1)))
            .andExpect(jsonPath("$.iso", is(country_info.getIso()))
        );
            
        verify(service, times(1)).getISObyName( "Portugal" );

    }


    @Test
    void getCacheStats() throws Exception {

        String cache_stats = "{ \"time_to_live\": 3000, \"requests\": 5, \"hits\": 3, \"misses\": 2 }";

    
        when( service.getCacheStats() ).thenReturn( cache_stats );

        mvc.perform(
            get("/info/cache").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.*", hasSize(4)))
            .andExpect(jsonPath("$.time_to_live", is(3000)))
            .andExpect(jsonPath("$.requests", is(5)))
            .andExpect(jsonPath("$.hits", is(3)))
            .andExpect(jsonPath("$.misses", is(2))
        );
            
        verify(service, times(1)).getCacheStats();

    }
    
}
