## Resolução do exercício 1 do guião 3

### a) Identify a couple of examples on the use of AssertJ expressive methods chaining.

AssertJ expressive methods chaining in this project:

* in the **A_EmployeeRepositoryTest.java**

```
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
```

* in the **B_EmployeeService_UnitTest.java**

```
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName());
```

* in the **C_EmployeeController_WithMockServiceTest.java**

```
nothing
```

* in the **D_EmployeeRestControllerIT.java**

```
assertThat(found).extracting(Employee::getName).containsOnly("bob");
```

* in the **E_EmployeeRestControllerTemplateIT.java**

```
assertThat(found).extracting(Employee::getName).containsOnly("bob");

assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");
```


### b) Identify an example in which you mock the behavior of the repository (and avoid involving a database). 

* in the **B_EmployeeService_UnitTest.java**

```java
@BeforeEach
public void setUp() {

    //these expectations provide an alternative to the use of the repository
    Employee john = new Employee("john", "john@deti.com");
    john.setId(111L);

    Employee bob = new Employee("bob", "bob@deti.com");
    Employee alex = new Employee("alex", "alex@deti.com");

    List<Employee> allEmployees = Arrays.asList(john, bob, alex);

    Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
    Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
    Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
    Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
    Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
    Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
}
```

The Mockito will mock and verify the behaviors of the employeeRepository


### c) What is the difference between standard @Mock and @MockBean?

**@Mock** (org.mockito.Mock)

    * Mark a field as a mock.
        --> Allows shorthand mock creation.
        --> Minimizes repetitive mock creation code.
        --> Makes the test class more readable.
        --> Makes the verification error easier to read because the field name is used to identify the mock.


**@MockBean** (org.springframework.boot.test.mock.mockito.MockBean)

    * Annotation that can be used to add mocks to a Spring ApplicationContext. Can be used as a class level annotation or on fields in either @Configuration classes, or test classes that are @RunWith the SpringRunner.

    * Mocks can be registered by type or by bean name. Any existing single bean of the same type defined in the context will be replaced by the mock, if no existing bean is defined a new one will be added.

    * When @MockBean is used on a field, as well as being registered in the application context, the mock will also be injected into the field.


### d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

This file is used to implement all the properties needed, usually to connect with other services to the context of the Application , like database credentials and urls. It can be used by our tests to connect to a real database.


### e) the sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?

The test C) mocks all service implementation and the access to API. <br>
The test D) encapsulates all web application beans and makes them available for testing. <br>
the test E) test all components of the project. It will access the API by direct request and not use any mock.
