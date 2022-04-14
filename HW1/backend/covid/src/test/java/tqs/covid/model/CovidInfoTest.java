package tqs.covid.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CovidInfoTest {

    @Test
    void getsTest(){
        
        CovidInfo info = new CovidInfo("1", "Portugal", "Europe", "PRT", "pt", 31, 111, 2, 555, 3, 222, 6, 10000000, 42, 76.8, 34.2);

        assertEquals("1", info.getId());
        assertEquals("Portugal", info.getCountry());
        assertEquals("Europe", info.getContinent());
        assertEquals("PRT", info.getIso());
        assertEquals("pt", info.getTwoLetterSymbol());

        assertEquals(111, info.getTotal_cases());
        assertEquals(2, info.getNew_cases());

        assertEquals(555, info.getTotal_deaths());
        assertEquals(3, info.getNew_deaths());

        assertEquals(222, info.getTotal_recovered());
        assertEquals(6, info.getNew_recovered());

        assertEquals(31, info.getRank());
        assertEquals(10000000, info.getPopulation());
        assertEquals(42, info.getSerious_critical());
        assertEquals(76.8, info.getTest_Percentage());
        assertEquals(34.2, info.getInfection_Risk());
    }
    
}
