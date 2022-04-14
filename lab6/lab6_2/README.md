
### Commands needed for this exercice

```
docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest

mvn verify sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.projectKey=euromillion_unit -Dsonar.login=56d018d5bcdeadb2f22d0cd89e7f264a92e0818c
```

### **f) Has your project passed the defined quality gate? Elaborate your answer**

It did pass with 1 bug, 0 vulnerabilities, 1 security hotspot, 2h 26min debt, 32 code smeels, 75.0% code coverage with 11 Unit Tests and 0% code duplication.


### **g) Explore the analysis results and complete with a few sample issues, as applicable.**

| Issue | Problem description | How to solve | 
| :---: | :---: | :---: |
| Bug | Save and re-use this "Random" |  Random should not be instantiated inside of a function. |
| Security Hotspots | Make sure that using this pseudorandom number generator is safe here |  Random should not be instantiated inside of a function. |
| Code smell (major) | Refactor the code in order to not assign to this loop counter from within the loop body | increment the variable within the for-structure |
| Code smell (major) | Replace this use of System.out or System.err by a logger | use a logger isntead of System.out.println() |