

## o que falta fazer

1. Implement a cache2to reduce the number of external accesses. Whenever a request is made to a remote API, you should cache the results; if the same (remote) data is requested(i.e., a duplicate request)then you should use the cached values. The cacheshould define a time-to-livepolicy and produce some basic statistics about its operation (count of requests, hits/misses). All data related to the cacheoperations is not required to be persisted[you mayuse a memory data structure, like a HashMap].

2. Your API should also provide an endpoint to get basic statistics on the use of the (internal) cache.

3. testes

4. sonarcloud

