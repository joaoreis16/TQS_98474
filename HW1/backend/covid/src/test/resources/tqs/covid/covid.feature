Feature: Covid
 
    Scenario: Search for Portugal covid-19 stats
        When I want to access "http://localhost:4200/world"
        And I click in "By Country" side bar button
        And I should see "Search for a specific country"
        And I fill the iso code input with "prt" and the country name input with "Portugal"
        And I click in search button
        Then I should see the following message "Covid-19 stats in the last 6 months"

    Scenario: See covid-19 stats of the 3rd country most affected 
        When I want to access "http://localhost:4200/world"
        And I click in 3 button in the table
        Then I should see the following message "Covid-19 stats in the last 6 months"

    Scenario: Search for an ISO code that doesn't exist
        When I want to access "http://localhost:4200/world"
        And I click in "By Country" side bar button
        And I should see "Search for a specific country"
        And I fill the iso code input with "iso"
        And I click in search button
        Then I should see the following message "Country not found :/ Please, try another one"