export interface Cache {
    time_to_live: number,
    requests: number,
    serious_critical: number,
    hits: number,
    misses: number
}