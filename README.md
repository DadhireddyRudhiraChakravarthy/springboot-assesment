#Customer API - Spring Boot Project

This is a simple Spring Boot application to manage customers. It supports basic operations like saving,getting customer details by (id,name and email) and deleting a customer.

---

##  Project Structure

src/
├── main/
│ └── java/
│ └── com.assesment.customer/
│ ├── controller/ # REST API controller
│ ├── entity/ # entity class
│ ├── exception/ # custom exceptions
│ ├── repository/ # JPA repository
│ ├── service/ 
│     ├── impl/ # Business logic
│ ├── util/ # common class
│ ├── response/ # response entity
│ └── SpringbootAssesmentApplication.java
└── test/
└── java/
└── com.assesment.customer.testController/
└── SpringbootAssesmentApplicationTests.java



---

##  Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database (for testing)
- JUnit 5
- Mockito
- Lombok
- Maven

---

##  How to Run

# 1. Build the project
mvn clean install

# 2. Run the application
mvn spring-boot:run
The app will run at:
http://localhost:8082/assesment

 API Endpoints
Method	Endpoint	                                                                Description
POST   http://localhost:8082/assesment/customer/saveCustomer                        save customer data 
GET	   http://localhost:8082/assesment/customer/getCustomerById?customerId={UUID}	Get customer by ID
GET	   http://localhost:8082/assesment/customer/getCustomerByEmail?customerEmail=	Get customer by email
GET	   http://localhost:8082/assesment/customer/getCustomerByName?customerName=	    Get customer by name
PUT    http://localhost:8082/assesment/customer/updateCustomer/                     update customer by id
DELETE http://localhost:8082/assesment/customer/deleteCustomer/                     delete customer by id

Sample Response (GET)

{
    "statusCode": 200,
    "status": "OK",
    "message": "Customer Fetched Successfully",
    "data": {
        "id": "544bf37f-8bea-41bf-a157-ea91f23e559c",
        "customerName": "chakris",
        "customerEmail": "chakravarthy@gmail.com",
        "annualSpend": 1001.50,
        "lastPurchaseDate": "2025-07-31T12:47:00",
        "tier": "Gold"
    }
}

Sample Response (POST)

{
    "statusCode": 200,
    "status": "OK",
    "message": "Customer saved successfully",
    "data": {
        "id": "544bf37f-8bea-41bf-a157-ea91f23e559c",
        "customerName": "chakris",
        "customerEmail": "chakravarthy@gmail.com",
        "annualSpend": 1001.5,
        "lastPurchaseDate": "2025-07-31T12:47:00",
        "tier": "Gold"
    }
}


##API Documentation (Swagger)

This project uses **Swagger UI** for interactive REST API documentation.
http://localhost:8082/assesment/swagger-ui/index.html


Testing

# Run tests
mvn test
CustomerControllerTest.java covers unit tests for REST APIs using MockMvc.

Uses @WebMvcTest and @MockBean for testing controller in isolation.

👤 Author
Dadhireddy Rudhira Chakravarthy reddy
GitHub: https://github.com/DadhireddyRudhiraChakravarthy/springboot-assesment

Email: chakravarthi1803@gmail.com