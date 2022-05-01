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

        assertEquals( "one", this.cache.get("um"));
        assertEquals( 1, this.cache.size());
    }
    

    @DisplayName("2. Clean item of the cache test")
    @Test
    public void cleanTest() {
        this.cache.add("um", "one");
        this.cache.add("dois", "two");
        assertEquals( 2, this.cache.size());

        this.cache.clean("um");
        assertEquals( 1, this.cache.size() );
        assertFalse( this.cache.clean("um") );        
    }


    @DisplayName("3. Get item of the cache test")
    @Test
    public void getTest() {
        this.cache.add("um", "one");
        this.cache.add("dois", "two");

        assertEquals( "two", this.cache.get("dois") );              // requests = 1 | misses = 0 | hits = 1
        assertNotEquals( "two", this.cache.get("um") );           // requests = 2 | misses = 0 | hits = 2
        assertEquals( null, this.cache.get("três") );               // requests = 3 | misses = 1 | hits = 2

        assertEquals( 3, this.cache.getRequests() ); 
        assertEquals( 2, this.cache.getHits() );   
        assertEquals( 1, this.cache.getMisses() );   
    }


    @DisplayName("4. Cache behaviour test")
    @Test
    public void behaviourTest() {
        this.cache.add("um", "one");
        this.cache.add("dois", "two");

        assertEquals( 2, this.cache.size() );
        assertEquals( "two", this.cache.get("dois") );

        try {
            Thread.sleep(3000); // sleep 3 seconds

            assertEquals( 0, this.cache.size() );
            assertEquals( null, this.cache.get("dois") );
            
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }
    }
}
