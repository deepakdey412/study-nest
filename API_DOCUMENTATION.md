# 📚 StudyNest API Documentation & Testing Guide

## 🚀 Quick Start

### Step 1: Start the Application
```bash
cd backend
./mvnw spring-boot:run
```

Wait for the message: `Started StudyNestApplication in X seconds`

### Step 2: Access Swagger UI
Open your browser and navigate to: **http://localhost:8080/swagger-ui.html**

---

## 📍 Important URLs

| Service | URL | Description |
|---------|-----|-------------|
| **Swagger UI** | http://localhost:8080/swagger-ui.html | 🎯 **START HERE** - Interactive API Testing |
| **OpenAPI JSON** | http://localhost:8080/v3/api-docs | OpenAPI 3.0 Specification |
| **H2 Console** | http://localhost:8080/h2-console | Database Console |
| **Base API** | http://localhost:8080/api | API Base Path |

### H2 Database Connection Details
- **JDBC URL**: `jdbc:h2:mem:studynest`
- **Username**: `sa`
- **Password**: *(empty)*

---

## 🧪 Complete Testing Guide Using Swagger UI

### 📖 What is Swagger UI?
Swagger UI is an interactive documentation tool that allows you to:
- ✅ View all available API endpoints
- ✅ Test APIs directly from your browser
- ✅ See request/response examples
- ✅ Understand data models and validation rules

---

## 🎯 Step-by-Step Testing Tutorial

### **STEP 1: Register a Student Account** 🎓

1. **Open Swagger UI**: http://localhost:8080/swagger-ui.html
2. **Find the "Authentication" section** (green header)
3. **Click on** `POST /api/auth/student/register`
4. **Click "Try it out"** button (top right of the endpoint)
5. **Edit the request body** with your details:

```json
{
  "name": "John Doe",
  "rollNo": "CS2024001",
  "prn": "PRN2024001",
  "semester": 6,
  "email": "john.doe@studynest.com",
  "password": "password123",
  "mobile": "9876543210"
}
```

6. **Click "Execute"** button
7. **Check the Response** (scroll down):
   - Status Code: `201` (Success)
   - Response Body contains your JWT token

8. **Copy the JWT Token** from the response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",  ← COPY THIS
  "role": "STUDENT",
  "email": "john.doe@studynest.com",
  "name": "John Doe"
}
```

---

### **STEP 2: Authorize with JWT Token** 🔐

1. **Scroll to the top** of Swagger UI
2. **Click the "Authorize" button** (🔓 lock icon, top right)
3. **Paste your token** in the "Value" field:
   ```
   eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
   ```
   ⚠️ **Important**: Paste ONLY the token value, do NOT add "Bearer" prefix
4. **Click "Authorize"** button
5. **Click "Close"** button
6. **You're now authenticated!** 🎉 (Lock icon changes to 🔒)

---

### **STEP 3: Get Your Student Profile** 👤

1. **Find the "Students" section**
2. **Click on** `GET /api/students/profile`
3. **Click "Try it out"**
4. **Click "Execute"**
5. **Check Response**:
   - Status Code: `200`
   - Your profile information is displayed

```json
{
  "id": 1,
  "name": "John Doe",
  "rollNo": "CS2024001",
  "prn": "PRN2024001",
  "semester": 6,
  "email": "john.doe@studynest.com",
  "mobile": "9876543210"
}
```

---

### **STEP 4: Browse Available Modules** 📚

1. **Find the "Modules" section**
2. **Click on** `GET /api/modules`
3. **Click "Try it out"**
4. **Click "Execute"**
5. **View all available modules** with their details

**OR** Get modules by semester:

1. **Click on** `GET /api/modules/semester/{semester}`
2. **Click "Try it out"**
3. **Enter semester number**: `6`
4. **Click "Execute"**
5. **View semester 6 modules**

---

### **STEP 5: Get Questions for a Module** ❓

1. **Find the "Questions" section**
2. **Click on** `GET /api/questions/module/{moduleId}`
3. **Click "Try it out"**
4. **Enter moduleId**: `1` (Computer Networks)
5. **Click "Execute"**
6. **View questions** (without answers - for students)

**Example Response:**
```json
[
  {
    "id": 1,
    "questionText": "What does TCP stand for?",
    "option1": "Transmission Control Protocol",
    "option2": "Transfer Control Protocol",
    "option3": "Transitional Control Protocol",
    "option4": "Transport Connection Protocol",
    "correctAnswer": null  ← Hidden for students
  }
]
```

---

### **STEP 6: Take a Test** 📝

1. **Find the "Tests" section**
2. **Click on** `POST /api/tests/submit`
3. **Click "Try it out"**
4. **Fill in your answers**:

```json
{
  "moduleId": 1,
  "answers": {
    "1": "Transmission Control Protocol",
    "2": "Router",
    "3": "DHCP",
    "4": "Resolve IP addresses to domain names",
    "5": "HyperText Transfer Protocol"
  }
}
```

**How to fill answers:**
- Key = Question ID (from Step 5)
- Value = Your selected answer (exact text from options)

5. **Click "Execute"**
6. **View your test results**:

```json
{
  "id": 1,
  "moduleName": "Computer Networks",
  "score": 5,
  "totalQuestions": 5,
  "percentage": 100.00,
  "status": "PASS",
  "completedAt": "2026-04-17T15:55:30.123456"
}
```

---

### **STEP 7: View All Your Test Results** 📊

1. **Find the "Tests" section**
2. **Click on** `GET /api/tests/results`
3. **Click "Try it out"**
4. **Click "Execute"**
5. **View all your completed tests** with scores and status

---

### **STEP 8: Register and Test as Tutor** 👨‍🏫

#### 8.1 Logout from Student Account
1. **Click "Authorize" button** (🔒 lock icon)
2. **Click "Logout"** button
3. **Click "Close"**

#### 8.2 Register as Tutor
1. **Find "Authentication" section**
2. **Click on** `POST /api/auth/tutor/register`
3. **Click "Try it out"**
4. **Enter tutor details**:

```json
{
  "name": "Dr. Jane Smith",
  "mobileNo": "9876543211",
  "email": "jane.smith@studynest.com",
  "password": "tutor123"
}
```

5. **Click "Execute"**
6. **Copy the JWT token** from response

#### 8.3 Authorize as Tutor
1. **Click "Authorize" button**
2. **Paste tutor token**
3. **Click "Authorize"** and **"Close"**

#### 8.4 View Questions with Answers (Tutor Only)
1. **Find "Questions" section**
2. **Click on** `GET /api/questions/module/{moduleId}/with-answers`
3. **Click "Try it out"**
4. **Enter moduleId**: `1`
5. **Click "Execute"**
6. **View questions WITH correct answers** ✅

```json
[
  {
    "id": 1,
    "questionText": "What does TCP stand for?",
    "option1": "Transmission Control Protocol",
    "option2": "Transfer Control Protocol",
    "option3": "Transitional Control Protocol",
    "option4": "Transport Connection Protocol",
    "correctAnswer": "Transmission Control Protocol"  ← Visible for tutors
  }
]
```

---

## 🎨 Understanding Swagger UI Interface

### Color Coding
- 🟢 **Green (POST)** - Create new resources
- 🔵 **Blue (GET)** - Retrieve data
- 🟡 **Yellow (PUT)** - Update resources
- 🔴 **Red (DELETE)** - Delete resources

### Sections
- **Authentication** - Register and login endpoints
- **Modules** - Browse learning modules
- **Questions** - Get test questions
- **Tests** - Submit tests and view results
- **Students** - Student profile management

### Response Codes
- ✅ **200** - Success
- ✅ **201** - Created successfully
- ⚠️ **400** - Bad request (validation error)
- ⚠️ **401** - Unauthorized (invalid/missing token)
- ⚠️ **404** - Not found
- ⚠️ **409** - Conflict (duplicate data)

---

## 🔍 Testing Scenarios

### Scenario 1: Complete Student Journey
1. ✅ Register as student
2. ✅ Login (or use registration token)
3. ✅ View profile
4. ✅ Browse modules by semester
5. ✅ Get questions for a module
6. ✅ Submit test answers
7. ✅ View test results

### Scenario 2: Tutor Access
1. ✅ Register as tutor
2. ✅ Login (or use registration token)
3. ✅ Browse all modules
4. ✅ View questions with answers
5. ❌ Cannot submit tests (student only)
6. ❌ Cannot view student profiles

### Scenario 3: Error Testing
1. Try registering with duplicate email → 409 Conflict
2. Try login with wrong password → 401 Unauthorized
3. Try accessing protected endpoint without token → 401 Unauthorized
4. Try submitting test with invalid moduleId → 404 Not Found
5. Try registering with invalid email format → 400 Bad Request

---

## 💡 Pro Tips for Testing

### Tip 1: Use Schema Tab
- Click on any endpoint
- Click "Schema" tab to see the data structure
- Understand required fields and data types

### Tip 2: Use Example Values
- Swagger auto-fills example values
- Modify them to test different scenarios

### Tip 3: Check Validation Errors
- Try submitting invalid data
- See detailed validation error messages
- Fix and retry

### Tip 4: Test Different Roles
- Keep two browser tabs open
- One for student, one for tutor
- Compare access differences

### Tip 5: Monitor Responses
- Always check the response status code
- Read error messages carefully
- Use them to debug issues

---

## 🐛 Common Issues & Solutions

### Issue 1: "401 Unauthorized" Error
**Problem**: Token expired or not set
**Solution**: 
1. Click "Authorize" button
2. Paste a fresh token
3. Click "Authorize"

### Issue 2: "403 Forbidden" Error
**Problem**: Wrong role (e.g., tutor trying student endpoint)
**Solution**: Use correct account type for the endpoint

### Issue 3: "400 Bad Request" Error
**Problem**: Invalid input data
**Solution**: Check validation errors in response, fix data format

### Issue 4: "409 Conflict" Error
**Problem**: Duplicate email/rollNo/PRN
**Solution**: Use different values for unique fields

### Issue 5: Can't See Endpoints
**Problem**: Application not running
**Solution**: 
```bash
cd backend
./mvnw spring-boot:run
```

---

## 🔐 Authentication

All endpoints except authentication endpoints require a JWT token in the Authorization header:

```
Authorization: Bearer <your-jwt-token>
```

---

## 📋 API Endpoints

### 1️⃣ Authentication Endpoints

#### Register Student
```http
POST /api/auth/student/register
Content-Type: application/json

{
  "name": "John Doe",
  "rollNo": "CS001",
  "prn": "PRN123456",
  "semester": 6,
  "email": "john.doe@example.com",
  "password": "password123",
  "mobile": "9876543210"
}
```

**Response (201 Created):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "role": "STUDENT",
  "email": "john.doe@example.com",
  "name": "John Doe"
}
```

---

#### Register Tutor
```http
POST /api/auth/tutor/register
Content-Type: application/json

{
  "name": "Dr. Jane Smith",
  "mobileNo": "9876543210",
  "email": "jane.smith@example.com",
  "password": "password123"
}
```

**Response (201 Created):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "role": "TUTOR",
  "email": "jane.smith@example.com",
  "name": "Dr. Jane Smith"
}
```

---

#### Login (Student or Tutor)
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "john.doe@example.com",
  "password": "password123"
}
```

**Response (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "role": "STUDENT",
  "email": "john.doe@example.com",
  "name": "John Doe"
}
```

---

### 2️⃣ Module Endpoints

#### Get All Modules
```http
GET /api/modules
Authorization: Bearer <token>
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "name": "Computer Networks",
    "semester": 6,
    "imageUrl": "/images/computer-networks.png",
    "pdfUrl": "https://drive.google.com/sample1",
    "videoLink": "https://youtu.be/VwN91x5i25g",
    "externalLinks": "https://www.geeksforgeeks.org/computer-network-tutorials/",
    "questionCount": 5
  },
  {
    "id": 2,
    "name": "Compiler Design",
    "semester": 6,
    "imageUrl": "/images/compiler-design.png",
    "pdfUrl": "https://drive.google.com/sample2",
    "videoLink": "https://youtu.be/XUsw5igq4DM",
    "externalLinks": "https://www.geeksforgeeks.org/compiler-design-tutorials/",
    "questionCount": 5
  }
]
```

---

#### Get Modules by Semester
```http
GET /api/modules/semester/{semester}
Authorization: Bearer <token>
```

**Example:**
```http
GET /api/modules/semester/6
Authorization: Bearer <token>
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "name": "Computer Networks",
    "semester": 6,
    "imageUrl": "/images/computer-networks.png",
    "pdfUrl": "https://drive.google.com/sample1",
    "videoLink": "https://youtu.be/VwN91x5i25g",
    "externalLinks": "https://www.geeksforgeeks.org/computer-network-tutorials/",
    "questionCount": 5
  }
]
```

---

#### Get Module by ID
```http
GET /api/modules/{id}
Authorization: Bearer <token>
```

**Example:**
```http
GET /api/modules/1
Authorization: Bearer <token>
```

**Response (200 OK):**
```json
{
  "id": 1,
  "name": "Computer Networks",
  "semester": 6,
  "imageUrl": "/images/computer-networks.png",
  "pdfUrl": "https://drive.google.com/sample1",
  "videoLink": "https://youtu.be/VwN91x5i25g",
  "externalLinks": "https://www.geeksforgeeks.org/computer-network-tutorials/",
  "questionCount": 5
}
```

---

### 3️⃣ Question Endpoints

#### Get Questions for Module (Student - Without Answers)
```http
GET /api/questions/module/{moduleId}
Authorization: Bearer <student-token>
```

**Example:**
```http
GET /api/questions/module/1
Authorization: Bearer <student-token>
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "questionText": "What does TCP stand for?",
    "option1": "Transmission Control Protocol",
    "option2": "Transfer Control Protocol",
    "option3": "Transitional Control Protocol",
    "option4": "Transport Connection Protocol",
    "correctAnswer": null
  },
  {
    "id": 2,
    "questionText": "Which device operates at the network layer?",
    "option1": "Router",
    "option2": "Switch",
    "option3": "Hub",
    "option4": "Repeater",
    "correctAnswer": null
  }
]
```

---

#### Get Questions for Module (Tutor - With Answers)
```http
GET /api/questions/module/{moduleId}/with-answers
Authorization: Bearer <tutor-token>
```

**Example:**
```http
GET /api/questions/module/1/with-answers
Authorization: Bearer <tutor-token>
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "questionText": "What does TCP stand for?",
    "option1": "Transmission Control Protocol",
    "option2": "Transfer Control Protocol",
    "option3": "Transitional Control Protocol",
    "option4": "Transport Connection Protocol",
    "correctAnswer": "Transmission Control Protocol"
  },
  {
    "id": 2,
    "questionText": "Which device operates at the network layer?",
    "option1": "Router",
    "option2": "Switch",
    "option3": "Hub",
    "option4": "Repeater",
    "correctAnswer": "Router"
  }
]
```

---

### 4️⃣ Test Endpoints

#### Submit Test
```http
POST /api/tests/submit
Authorization: Bearer <student-token>
Content-Type: application/json

{
  "moduleId": 1,
  "answers": {
    "1": "Transmission Control Protocol",
    "2": "Router",
    "3": "DHCP",
    "4": "Resolve IP addresses to domain names",
    "5": "HyperText Transfer Protocol"
  }
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "moduleName": "Computer Networks",
  "score": 5,
  "totalQuestions": 5,
  "percentage": 100.00,
  "status": "PASS",
  "completedAt": "2026-04-17T15:55:30.123456"
}
```

---

#### Get All Test Results
```http
GET /api/tests/results
Authorization: Bearer <student-token>
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "moduleName": "Computer Networks",
    "score": 5,
    "totalQuestions": 5,
    "percentage": 100.00,
    "status": "PASS",
    "completedAt": "2026-04-17T15:55:30.123456"
  },
  {
    "id": 2,
    "moduleName": "Machine Learning",
    "score": 3,
    "totalQuestions": 5,
    "percentage": 60.00,
    "status": "PASS",
    "completedAt": "2026-04-17T16:10:45.789012"
  }
]
```

---

### 5️⃣ Student Profile Endpoint

#### Get Student Profile
```http
GET /api/students/profile
Authorization: Bearer <student-token>
```

**Response (200 OK):**
```json
{
  "id": 1,
  "name": "John Doe",
  "rollNo": "CS001",
  "prn": "PRN123456",
  "semester": 6,
  "email": "john.doe@example.com",
  "mobile": "9876543210"
}
```

---

## 🔒 Role-Based Access Control

| Endpoint | Student | Tutor |
|----------|---------|-------|
| POST /api/auth/student/register | ✅ Public | ✅ Public |
| POST /api/auth/tutor/register | ✅ Public | ✅ Public |
| POST /api/auth/login | ✅ Public | ✅ Public |
| GET /api/modules | ✅ | ✅ |
| GET /api/modules/semester/{semester} | ✅ | ✅ |
| GET /api/modules/{id} | ✅ | ✅ |
| GET /api/questions/module/{moduleId} | ✅ | ❌ |
| GET /api/questions/module/{moduleId}/with-answers | ❌ | ✅ |
| POST /api/tests/submit | ✅ | ❌ |
| GET /api/tests/results | ✅ | ❌ |
| GET /api/students/profile | ✅ | ❌ |

---

## ⚠️ Error Responses

### 400 Bad Request - Validation Error
```json
{
  "timestamp": "2026-04-17T15:55:30.123456",
  "status": 400,
  "error": "Validation Failed",
  "message": "Invalid input data",
  "path": "/api/auth/student/register",
  "validationErrors": {
    "email": "Invalid email format",
    "password": "Password must be at least 6 characters"
  }
}
```

### 401 Unauthorized - Invalid Credentials
```json
{
  "timestamp": "2026-04-17T15:55:30.123456",
  "status": 401,
  "error": "Unauthorized",
  "message": "Invalid email or password",
  "path": "/api/auth/login"
}
```

### 404 Not Found - Resource Not Found
```json
{
  "timestamp": "2026-04-17T15:55:30.123456",
  "status": 404,
  "error": "Not Found",
  "message": "Module not found with id: 999",
  "path": "/api/modules/999"
}
```

### 409 Conflict - Duplicate Resource
```json
{
  "timestamp": "2026-04-17T15:55:30.123456",
  "status": 409,
  "error": "Conflict",
  "message": "Email already exists",
  "path": "/api/auth/student/register"
}
```

---

## 🧪 Testing with cURL

### Register a Student
```bash
curl -X POST http://localhost:8080/api/auth/student/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "rollNo": "CS001",
    "prn": "PRN123456",
    "semester": 6,
    "email": "john.doe@example.com",
    "password": "password123",
    "mobile": "9876543210"
  }'
```

### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john.doe@example.com",
    "password": "password123"
  }'
```

### Get All Modules (with token)
```bash
curl -X GET http://localhost:8080/api/modules \
  -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE"
```

### Submit Test
```bash
curl -X POST http://localhost:8080/api/tests/submit \
  -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE" \
  -H "Content-Type: application/json" \
  -d '{
    "moduleId": 1,
    "answers": {
      "1": "Transmission Control Protocol",
      "2": "Router",
      "3": "DHCP",
      "4": "Resolve IP addresses to domain names",
      "5": "HyperText Transfer Protocol"
    }
  }'
```

---

## 🧪 Testing with Postman

1. **Import Collection**: Create a new collection in Postman
2. **Set Base URL**: `http://localhost:8080`
3. **Add Authorization**: 
   - Type: Bearer Token
   - Token: `<your-jwt-token>`
4. **Test Endpoints**: Use the examples above

---

## 📊 Database Schema

### Tables
1. **students** - Student information
2. **tutors** - Tutor information
3. **modules** - Learning modules
4. **questions** - MCQ questions
5. **student_results** - Test results

### Sample Data
The application comes pre-loaded with:
- 5 modules (Computer Networks, Compiler Design, Machine Learning, Data Structures, Operating Systems)
- 25 questions (5 per module)

---

## 🔧 Configuration

### JWT Configuration
- **Secret Key**: Configured in `application.properties`
- **Token Expiration**: 24 hours (86400000 ms)

### Database Configuration
- **Type**: H2 In-Memory Database
- **Mode**: create-drop (recreates on each restart)
- **Console**: Enabled at `/h2-console`

---

## 🚀 Running the Application

### Using Maven
```bash
cd Study-Nest-Backend/Study-Nest
./mvnw spring-boot:run
```

### Using JAR
```bash
cd Study-Nest-Backend/Study-Nest
./mvnw clean package -DskipTests
java -jar target/Study-Nest-0.0.1-SNAPSHOT.jar
```

---

## 📝 Notes

1. **Pass Percentage**: Students need 40% or above to pass a test
2. **Token Validity**: JWT tokens are valid for 24 hours
3. **Database**: H2 in-memory database - data is lost on restart
4. **CORS**: Configured to allow requests from `http://localhost:4200` (Angular frontend)
5. **Security**: All endpoints except `/api/auth/**`, `/h2-console/**`, and Swagger endpoints require authentication

---

## 🎯 Next Steps

1. Visit **Swagger UI**: http://localhost:8080/swagger-ui.html
2. Test the endpoints using the interactive documentation
3. Register a student and tutor account
4. Login and get JWT tokens
5. Test protected endpoints with the tokens

---

## 📞 Support

For issues or questions, please refer to the project README or contact the development team.

---

**Last Updated**: April 17, 2026
**Version**: 1.0.0
**Status**: ✅ Production Ready
