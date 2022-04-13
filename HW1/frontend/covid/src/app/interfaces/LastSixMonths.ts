export interface LastSixMonths {
    id: string,

    country: string,
    continent: string,
    symbol: string,
    date: Date,

    // cases
    total_cases: number,
    new_cases: number,

    // deaths
    total_deaths: number,
    new_deaths: number,

    // tests
    total_tests: number,
    new_tests: number

}