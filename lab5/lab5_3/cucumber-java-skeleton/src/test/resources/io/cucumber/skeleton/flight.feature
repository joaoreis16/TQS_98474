Feature: Flights

  Scenario: Booking a trip from Philadelphia to Dublin
    When I want to access "https://blazedemo.com/"
    And I choose my departure city "Philadelphia" and destination "Dublin"
    And I click Find Flights button
    And I should see flights of "Flights from Philadelphia to Dublin"
    And I choose a flight and click Choose This Flight button
    And I fill my personal informations: name "João Reis", street "rua da velhice", city "ovar", state "gz"
    And I click Purchase Flight button
    Then I should see the following message "Thank you for your purchase today!"