# Store Management Tool

This is a Spring Boot application for store management having the neccessary APIs for perfoming CRUD operations.

## Prerequisites
- JDK 17 or greater
- Chrome or Firefox Browser
  
## Setup and Application Running
**1. Clone locally the repository**
```bash
https://github.com/AstridSilaghi/mgmt_store.git
```
**2.1 Start the spring boot application**
```bash
mvn spring-boot:run
```
**OR**

**2.2 Import the project as "Maven project" in your favourite IDE and run as "Java Application"**

The app will start running at http://localhost:8081.
The port can be changed by setting -Dserver.port argument in "Run configuration".
   
## About the App
CRUD (Create, Read, Update, Delete) operations can be performed via REST APIs on products and orders.
An order can have non or more products.
If a product is not on stock then it can not be added in an order.

## Explore REST APIs
+ GET /product
+ GET /product/find-product{id}
+ GET /product/product-price-min
+ GET /product/on-stock
+ POST /product/add-product
+ PUT /product/change-price{id}
+ PUT /product/change-name{id}
+ DELETE /product/remove-all
+ GET /order
+ GET /oder/find-order-id{id}
+ GET /oder/find-order-number{number}
+ POST /order/add-order
+ PUT /orde/update-order[id}
+ PUT /order/update-order{order_id}/product{prod_id}

## To view Swagger 3 API docs
Start the server and browse to http://localhost:8081/swagger-ui/index.html .

Alternatively, Postman can be used to make requests.

## To view the H2 in-memory database
To view and query the database you can browse to http://localhost:8081/h2-console using username: 'admin' with password: "password".
