# Almacen Accenture

_Initial proyect based on the managament of orders of the clients of certain store and how this interactions given certain conditions_

## Starting

_This proyect was made following the concept 
of SOA - Service Oriented Architecture, this proyect was made on JAVA Version 16, SpringBoot Framework 2.4.4, the appropiates libraries and the Maven dependencies._

### Pre-requirements 

_Getting Started_

```
Generate a project on Spring Initialzr: https://start.spring.io and Follow this options;
* Project: Maven Project
* Language: Java
* Spring Boot: 2.4.4
* Java 16 

 and for the dependencies:
- JPA
- SpringBoot DevTools
- MySQL connector

To test the code is recommended to have:
* Postman
```

### Instalation

_First steps_

```
git clone https://github.com/Doate/AlmacenAccenture.git
```

```
Here is all set and you can make the neccesary http petitions for each entity found on the project

+ Clients
|-GET -> /clients
|-GET -> /clients/{id}
|-POST -> /clients
|-DELETE -> /clients/{id}

+ Products
|-GET -> /products
|-GET -> /products/{id}
|-POST -> /products
|-DELETE -> /products/{id}

+ Orders
|-GET -> /orders
|-GET -> /orders/{id}
|-GET -> /clients/{idClient}/orders
|-POST -> /clients/{idClient}/orders
|-POST -> /clients/{idClient}/orders/{idOrder}/addProduct/{Productid}
|-PUT -> /clients/{idClient}/orders/{idOrder}
|-DELETE -> /clients/{idClient}/orders/{idOrder}

+ Bills
|-GET -> /bills
|-GET -> /bills/{Billid}
```

_Example of a petition to create a client and
add it to the list of clients_
```
Create a new client
curl --location --request POST 'localhost:8080/clients' \
--header 'Content-Type: application/json' \
--data-raw '{
    "idClient": 3,
    "idDocument": "123",
    "name": "ClienteJoselito",
    "address": "Carrera 11# 14-108"
}'

Response and Get Client method
[
    {
        "idClient": 1,
        "idDocument": "12345",
        "name": "ClienteDorian",
        "address": "Carrera 11# 14-08"
    },
    {
        "idClient": 2,
        "idDocument": "1234",
        "name": "ClienteTeresa",
        "address": "Carrera 11# 14-09"
    },
    {
        "idClient": 3,
        "idDocument": "123",
        "name": "ClienteJoselito",
        "address": "Carrera 11# 14-108"
    }
]
```

## Testing and unit testing
_The Unit testing was made with JUnit 3_

```
From the SpringToolSuit you can run the relevant test with the option "Run as 1 Junit test"
```
_Certain unit tests were made for the methods on the [DAO] package of the project with the objective of checking if this methods are working as intended and are related to the conditions provided_


## Deployement

_Coming soong (maybe?)_

## Built with


* [SpringInitializer](https://start.spring.io/) - [SpringToolSuite] - Project Generator
* [Maven](https://maven.apache.org/) - Dependencies management
* [JAVA](https://docs.oracle.com/en/java/) - Progamming Language


## Author

* **Dorian Ahumada** - [Doate](https://github.com/Doate)

## License

This proyect is protected by the  (GNU) License - see the [LICENSE.md](LICENSE.md) file for more details


