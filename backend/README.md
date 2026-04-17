# StudyNest Backend

## 🚀 Quick Start

### Run the Application
```bash
./mvnw spring-boot:run
```

### Access Points
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **H2 Console**: http://localhost:8080/h2-console
- **API Base**: http://localhost:8080/api

### H2 Database
- **URL**: `jdbc:h2:mem:studynest`
- **Username**: `sa`
- **Password**: *(empty)*

## 📚 Documentation
See [API_DOCUMENTATION.md](../API_DOCUMENTATION.md) for complete testing guide.

## 🔧 Build Commands
```bash
# Clean and compile
./mvnw clean compile

# Run tests
./mvnw test

# Package as JAR
./mvnw clean package

# Run JAR
java -jar target/Study-Nest-0.0.1-SNAPSHOT.jar
```

## ✅ Verify Application is Running
Open: http://localhost:8080/swagger-ui.html

You should see the Swagger UI with all API endpoints.
