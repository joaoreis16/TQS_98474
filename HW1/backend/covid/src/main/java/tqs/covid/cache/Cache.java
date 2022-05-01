package tqs.covid.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cache {

    private long max_time;
    private Map<String, Object> cache;
    private Map<String, Long> time2live;

    // statistics
    private int requests = 0;
    private int hits = 0;
    private int misses = 0;


    public Cache() {
        max_time = (long) 100 * 1000;
        cache = new HashMap<>();
        time2live = new HashMap<>();
    }

    public Cache(long max_time) {
        this.max_time = max_time * 1000;
        cache = new HashMap<>();
        time2live = new HashMap<>();
    }



    //////////////////////// SOME NEEDED METHODS ////////////////////////
    
    public void add( String key, Object object ) {
        cache.put(key, object);
        time2live.put(key, System.currentTimeMillis() + max_time );
    }

    public boolean clean(String key) {
        if ( cache.containsKey(key) ){
            cache.remove(key);
            time2live.remove(key);
            return true;
        }
        return false;
    }
    
    public Object get(String key) {
        this.requests++;

        if ( cache.containsKey(key) && time2live.get(key) > System.currentTimeMillis()){
            this.hits++;
            return cache.get(key);

        } else if (cache.containsKey(key) && time2live.get(key) <= System.currentTimeMillis()) {
            clean(key);
        }

        this.misses++;
        return null;
    }

    public void clear() {
        cache.clear();
        time2live.clear();
    }

    public int size() {

        ArrayList<String> keys2remove = new ArrayList<>();

        for (String key : cache.keySet()) {
            if ( time2live.get(key) <= System.currentTimeMillis() ) {
                keys2remove.add(key);
            }
        }

        for (String key : keys2remove) clean(key);

        return this.cache.size();
    }


    //////////////////////// GETTERS & SETTERS ////////////////////////

    public long getMax_time() {
        return this.max_time;
    }

    public void setMax_time(long max_time) {
        this.max_time = max_time * 1000;
    }

    public int getRequests() {
        return this.requests;
    }

    public int getHits() {
        return this.hits;
    }

    public int getMisses() {
        return this.misses;
    }


    @Override
    public String toString() {
        return "{" +
            " \"time_to_live\": " + getMax_time() +
            ", \"requests\": " + getRequests() +
            ", \"hits\": " + getHits() +
            ", \"misses\": " + getMisses() +
            " }";
    }
    

    
}
