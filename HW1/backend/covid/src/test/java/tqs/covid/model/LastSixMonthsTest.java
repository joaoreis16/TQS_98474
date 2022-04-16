package tqs.covid.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class LastSixMonthsTest {

    @Test
    void getLastSixMonthsObjectTest() throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        Date date = formatter.parse("2022-04-14");
        
        LastSixMonths info = new LastSixMonths("1", "PRT", "Portugal", "Europe", date, 111L, 2L, 555L, 3L, 222L, 6L);

        assertEquals("1", info.getId());
        assertEquals("Portugal", info.getCountry());
        assertEquals("Europe", info.getContinent());
        assertEquals("PRT", info.getSymbol());
        assertEquals(date, info.getDate());

        assertEquals(111, info.getTotal_cases());
        assertEquals(2, info.getNew_cases());

        assertEquals(555, info.getTotal_deaths());
        assertEquals(3, info.getNew_deaths());

        assertEquals(222, info.getTotal_tests());
        assertEquals(6, info.getNew_tests());

    }
}
