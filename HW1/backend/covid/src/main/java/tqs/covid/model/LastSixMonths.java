package tqs.covid.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LastSixMonths {

    private String id;
    private String symbol;
    private String country;
    private String continent;
    private Date date;

    private Long total_cases;
    private Long new_cases;
    private Long total_deaths;
    private Long new_deaths;
    private Long total_tests;
    private Long new_tests;


    public LastSixMonths() {
    }


    public LastSixMonths(String id, String symbol, String country, String continent, Date date, Long total_cases, Long new_cases, Long total_deaths, Long new_deaths, Long total_tests, Long new_tests) {
        this.id = id;
        this.symbol = symbol;
        this.country = country;
        this.continent = continent;
        this.date = date;
        this.total_cases = total_cases;
        this.new_cases = new_cases;
        this.total_deaths = total_deaths;
        this.new_deaths = new_deaths;
        this.total_tests = total_tests;
        this.new_tests = new_tests;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContinent() {
        return this.continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getTotal_cases() {
        return this.total_cases;
    }

    public void setTotal_cases(Long total_cases) {
        this.total_cases = total_cases;
    }

    public Long getNew_cases() {
        return this.new_cases;
    }

    public void setNew_cases(Long new_cases) {
        this.new_cases = new_cases;
    }

    public Long getTotal_deaths() {
        return this.total_deaths;
    }

    public void setTotal_deaths(Long total_deaths) {
        this.total_deaths = total_deaths;
    }

    public Long getNew_deaths() {
        return this.new_deaths;
    }

    public void setNew_deaths(Long new_deaths) {
        this.new_deaths = new_deaths;
    }

    public Long getTotal_tests() {
        return this.total_tests;
    }

    public void setTotal_tests(Long total_tests) {
        this.total_tests = total_tests;
    }

    public Long getNew_tests() {
        return this.new_tests;
    }

    public void setNew_tests(Long new_tests) {
        this.new_tests = new_tests;
    }

    @Override
    public String toString() {

        String date1 = new SimpleDateFormat("yyyy-MM-dd").format( this.getDate() );

        return "{" +
            " \"id\":'" + getId() + "'" +
            ", \"symbol\":'" + getSymbol() + "'" +
            ", \"Country\":'" + getCountry() + "'" +
            ", \"Continent\":'" + getContinent() + "'" +
            ", \"date\":'" + date1 + "'" +
            ", \"total_cases\":'" + getTotal_cases() + "'" +
            ", \"new_cases\":'" + getNew_cases() + "'" +
            ", \"total_deaths\":'" + getTotal_deaths() + "'" +
            ", \"new_deaths\":'" + getNew_deaths() + "'" +
            ", \"total_tests\":'" + getTotal_tests() + "'" +
            ", \"new_tests\":'" + getNew_tests() + "'" +
            "}";                
    }
    
}
