package tqs.covid.cache;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CacheTest {

    private Cache cache;


    @BeforeEach
    void start() throws InterruptedException {
        this.cache = new Cache(2); // 2 seconds of life :)
    }

    @AfterEach
    void end(){
        this.cache.clear();
    }

    @DisplayName("1. Add item to cache test")
    @Test
    public void addTest() {
        this.cache.add("um", "one");

        assertEquals( this.cache.get("um"), "one");
        assertEquals( this.cache.size(), 1);
    }
    

    @DisplayName("2. Clean item of the cache test")
    @Test
    public void cleanTest() {
        this.cache.add("um", "one");
        this.cache.add("dois", "two");
        assertEquals( this.cache.size(), 2);

        this.cache.clean("um");
        assertEquals( this.cache.size(), 1);
        assertFalse( this.cache.clean("um") );        
    }


    @DisplayName("3. Get item of the cache test")
    @Test
    public void getTest() {
        this.cache.add("um", "one");
        this.cache.add("dois", "two");

        assertEquals( this.cache.get("dois"), "two");      // requests = 1 | misses = 0 | hits = 1
        assertNotEquals( this.cache.get("um"), "two");     // requests = 2 | misses = 0 | hits = 2
        assertEquals( this.cache.get("três"), null );      // requests = 3 | misses = 1 | hits = 2

        assertEquals( this.cache.getRequests(), 3); 
        assertEquals( this.cache.getHits(), 2);   
        assertEquals( this.cache.getMisses(), 1);   
    }


    @DisplayName("4. Cache behaviour test")
    @Test
    public void behaviourTest() {
        this.cache.add("um", "one");
        this.cache.add("dois", "two");

        assertEquals( this.cache.size(), 2);
        assertEquals( this.cache.get("dois"), "two");

        try {
            Thread.sleep(3000); // sleep 3 seconds

        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }

        assertEquals( this.cache.size(), 0);
        assertEquals( this.cache.get("dois"), null);
    }
}
