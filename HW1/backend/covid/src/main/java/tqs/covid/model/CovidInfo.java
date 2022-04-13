package tqs.covid.model;

public class CovidInfo {
    
    private String id; 
    private String country;
    private String continent;
    private String iso;
    private String TwoLetterSymbol;

    private long rank;
    private long total_cases;
    private long new_cases;
    private long total_deaths;
    private long new_deaths;
    private long total_recovered;
    private long new_recovered;
    private long population;
    private long serious_critical;
    private Double test_Percentage;
    private Double infection_Risk;


    public CovidInfo() {
    }


    public CovidInfo(String id, String country, String continent, String iso, String TwoLetterSymbol, long rank, long total_cases, long new_cases, long total_deaths, long new_deaths, long total_recovered, long new_recovered, long population, long serious_critical, Double test_Percentage, Double infection_Risk) {
        this.id = id;
        this.country = country;
        this.continent = continent;
        this.iso = iso;
        this.TwoLetterSymbol = TwoLetterSymbol;
        this.rank = rank;
        this.total_cases = total_cases;
        this.new_cases = new_cases;
        this.total_deaths = total_deaths;
        this.new_deaths = new_deaths;
        this.total_recovered = total_recovered;
        this.new_recovered = new_recovered;
        this.population = population;
        this.serious_critical = serious_critical;
        this.test_Percentage = test_Percentage;
        this.infection_Risk = infection_Risk;
    }


    public Double getTest_Percentage() {
        return this.test_Percentage;
    }

    public void setTest_Percentage(Double test_Percentage) {
        this.test_Percentage = test_Percentage;
    }

    public Double getInfection_Risk() {
        return this.infection_Risk;
    }

    public void setInfection_Risk(Double infection_Risk) {
        this.infection_Risk = infection_Risk;
    }


    public String getTwoLetterSymbol() {
        return this.TwoLetterSymbol;
    }

    public void setTwoLetterSymbol(String TwoLetterSymbol) {
        this.TwoLetterSymbol = TwoLetterSymbol;
    }


    public String getContinent() {
        return this.continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public long getRank() {
        return this.rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getIso() {
        return this.iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public long getTotal_cases() {
        return this.total_cases;
    }

    public void setTotal_cases(long total_cases) {
        this.total_cases = total_cases;
    }

    public long getNew_cases() {
        return this.new_cases;
    }

    public void setNew_cases(long new_cases) {
        this.new_cases = new_cases;
    }

    public long getTotal_deaths() {
        return this.total_deaths;
    }

    public void setTotal_deaths(long total_deaths) {
        this.total_deaths = total_deaths;
    }

    public long getNew_deaths() {
        return this.new_deaths;
    }

    public void setNew_deaths(long new_deaths) {
        this.new_deaths = new_deaths;
    }

    public long getTotal_recovered() {
        return this.total_recovered;
    }

    public void setTotal_recovered(long total_recovered) {
        this.total_recovered = total_recovered;
    }

    public long getNew_recovered() {
        return this.new_recovered;
    }

    public void setNew_recovered(long new_recovered) {
        this.new_recovered = new_recovered;
    }

    public long getPopulation() {
        return this.population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public long getSerious_critical() {
        return this.serious_critical;
    }

    public void setSerious_critical(long serious_critical) {
        this.serious_critical = serious_critical;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", country='" + getCountry() + "'" +
            ", continent='" + getContinent() + "'" +
            ", iso='" + getIso() + "'" +
            ", TwoLetterSymbol='" + getTwoLetterSymbol() + "'" +
            ", rank='" + getRank() + "'" +
            ", total_cases='" + getTotal_cases() + "'" +
            ", new_cases='" + getNew_cases() + "'" +
            ", total_deaths='" + getTotal_deaths() + "'" +
            ", new_deaths='" + getNew_deaths() + "'" +
            ", total_recovered='" + getTotal_recovered() + "'" +
            ", new_recovered='" + getNew_recovered() + "'" +
            ", population='" + getPopulation() + "'" +
            ", serious_critical='" + getSerious_critical() + "'" +
            ", test_Percentage='" + getTest_Percentage() + "'" +
            ", infection_Risk='" + getInfection_Risk() + "'" +
            "}";
    }
}