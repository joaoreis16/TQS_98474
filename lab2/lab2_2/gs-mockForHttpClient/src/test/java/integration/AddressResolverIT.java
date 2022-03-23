package integration;

import connection.ISimpleHttpClient;
import connection.TqsBasicHttpClient;
import geocoding.Address;
import geocoding.AddressResolver;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressResolverIT {

    private AddressResolver resolver = new AddressResolver( new TqsBasicHttpClient() );

    @Test
    void whenResolveAlboiGps_returnCaisAlboiAddress() throws ParseException, IOException, URISyntaxException {

        Optional<Address> result = resolver.findAddressForLocation(40.640661, -8.656688);

        assertEquals( result, Optional.of( new Address( "Cais do Alboi", "Glória e Vera Cruz", "Centro", "3800-246", null) ) );

    }

    
    @Test
    public void whenBadCoordidates_thenReturnNoValidAddress() throws IOException, URISyntaxException, ParseException {

        assertThrows( IllegalArgumentException.class, () -> { resolver.findAddressForLocation(-300, -810); } );

    }

}
