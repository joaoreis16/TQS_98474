/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package euromillions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
 * @author ico0
 */
public class DipTest {

    private Dip instance;


    public DipTest() {
    }

    @BeforeEach
    public void setUp() {
        instance = new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{1, 2});
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }


    @Test
    public void testConstructorFromBadArrays() {
        // todo: instantiate a dip passing valid or invalid arrays
         
        int wrong_numbers[] = {10, 20, 30, 40, 50, 60};
        int stars[] = {1, 2, 3};

        assertThrows( IllegalArgumentException.class , () -> { new Dip(wrong_numbers, stars); } , "IllegalArgumentException: wrong number of numbers :/");        
        
        int numbers[] = {10, 20, 30, 40, 50};
        int wrong_stars[] = {1, 2, 3, 4};

        assertThrows( IllegalArgumentException.class , () -> { new Dip(numbers, wrong_stars); } , "IllegalArgumentException: wrong number of stars :/");
    }

    @Test
    public void testFormat() {
        // note: correct the implementation of the format(), not the test...
        String result = instance.format();
        assertEquals("N[ 10 20 30 40 50] S[  1  2]", result, "format as string: formatted string not as expected. ");
    }


    @Test
    public void testNewRules() {
        // 2.b)
        int numbers[] = {10, 20, 30, 40, 50};
        int stars[] = {10, 20};

        assertThrows( IllegalArgumentException.class , () -> { new Dip(numbers, stars); } , "IllegalArgumentException: range of stars out of bound :/");

    }

}
