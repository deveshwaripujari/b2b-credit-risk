# B2B Credit Risk Recommendation System

## 🔹 Day 1: Backend Core + DB + File Parsing

### ✅ Features Implemented:
- ✅ Set up Maven project with Hibernate + MySQL integration
- ✅ Created entity models: `Customer`, `Invoice`
- ✅ Configured `persistence.xml` for Hibernate
- ✅ Built DAO layer: `InvoiceDAO`, `InvoiceImporter`
- ✅ Parsed XML/JSON invoices to Java using DOM + org.json
- ✅ Inserted parsed data into MySQL using Hibernate
- ✅ Wrote & passed JUnit tests for DAO and parser logic

### Technologies Used:
- Java 8, Maven, Hibernate (JPA), MySQL
- JSON & XML parsing (DOM & org.json)
- JUnit 4, Assert methods
- Tested with `mvn clean test`


## 🔷 Day 2: REST API + DAO Verification

✅ Implemented `InvoiceResource` and `CustomerResource` with:
- `GET /api/invoice/{id}`: Fetch invoice with customer info
- `POST /api/invoice/upload`: Upload invoice JSON/XML to DB
- `GET /api/customer/{id}/limit`: Credit scoring logic (basic)

✅ Verified using:
- `curl` commands for GET and POST
- Database insertions via Hibernate
- WAR packaging and local Tomcat deployment

 Technologies Used:
- Jersey (JAX-RS), JSON/XML annotations
- Hibernate, MySQL, Maven
- `curl`, `unzip`, Tomcat logs

 All endpoints working at:
- http://localhost:8080/b2b/api/invoice/1
- http://localhost:8080/b2b/api/customer/1/limit

