export interface CovidInfo {
    id: number,

    country: string,
    continent: string,
    iso: string,
    twoLetterSymbol: string,
    rank: number,
    population: number,
    serious_critical: number,
    test_Percentage: number,
    infection_Risk: number,


    // cases
    total_cases: number,
    new_cases: number,

    // deaths
    total_deaths: number,
    new_deaths: number,

    // recovered
    total_recovered: number,
    new_recovered: number

}