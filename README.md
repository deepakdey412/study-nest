# 📚 StudyNest - Learning Management System

A full-stack Learning Management System built with Spring Boot and React for managing student courses, tests, and certificates.

## 🚀 Features

- **Student Management**: Register, login, and manage student profiles
- **Module System**: 25 modules across 5 semesters (Semester 2-6)
- **MCQ Tests**: 10 questions per module with randomized options
- **Results Tracking**: View test results with scores and pass/fail status
- **Certificate Generation**: Earn certificates based on semester performance
- **Semester-Based Access**: Students can only take tests for their current semester
- **Role-Based Access**: Separate interfaces for Students and Tutors

## 🛠️ Tech Stack

### Backend
- **Framework**: Spring Boot 3.5.5
- **Language**: Java 17
- **Database**: H2 (In-memory)
- **Security**: Spring Security + JWT
- **Build Tool**: Maven

### Frontend
- **Framework**: React 18
- **Build Tool**: Vite
- **UI Library**: React Bootstrap
- **Routing**: React Router v6
- **HTTP Client**: Axios
- **Icons**: React Icons

## 📁 Project Structure

```
study-nest/
├── backend/                 # Spring Boot backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/       # Java source code
│   │   │   └── resources/  # Application properties & SQL
│   │   └── test/           # Test files
│   └── pom.xml             # Maven configuration
│
└── frontend/               # React frontend
    ├── src/
    │   ├── components/     # Reusable components
    │   ├── pages/          # Page components
    │   ├── services/       # API services
    │   └── App.jsx         # Main app component
    ├── package.json        # NPM dependencies
    └── vite.config.js      # Vite configuration
```

## 🚦 Getting Started

### Prerequisites
- Java 17 or higher
- Node.js 16 or higher
- Maven 3.6+

### Backend Setup

1. Navigate to backend directory:
```bash
cd backend
```

2. Run the application:
```bash
./mvnw spring-boot:run
```

Backend will start on `http://localhost:8080`

### Frontend Setup

1. Navigate to frontend directory:
```bash
cd frontend
```

2. Install dependencies:
```bash
npm install
```

3. Start development server:
```bash
npm run dev
```

Frontend will start on `http://localhost:5173`

## 📊 Database Schema

### Main Entities
- **Student**: User accounts for students
- **Tutor**: User accounts for tutors
- **Module**: Course modules (25 total)
- **Question**: MCQ questions (250 total, 10 per module)
- **StudentResult**: Test results and scores

## 🔐 Authentication

The system uses JWT-based authentication:
- Students register with: Name, PRN, Roll No, Email, Mobile, Semester
- Tutors register with: Name, Email, Specialization
- JWT tokens are stored in localStorage
- Protected routes require valid authentication

## 📝 API Endpoints

### Authentication
- `POST /api/auth/student/register` - Register student
- `POST /api/auth/student/login` - Student login
- `POST /api/auth/tutor/register` - Register tutor
- `POST /api/auth/tutor/login` - Tutor login

### Students
- `GET /api/students/profile` - Get student profile
- `PUT /api/students/profile` - Update student profile

### Modules
- `GET /api/modules` - Get all modules
- `GET /api/modules/{id}` - Get module by ID
- `GET /api/modules/semester/{semester}` - Get modules by semester

### Questions
- `GET /api/questions/module/{moduleId}` - Get questions for a module

### Tests
- `POST /api/tests/submit` - Submit test answers
- `GET /api/tests/results` - Get student results

### Certificate
- `GET /api/certificate/eligibility` - Check certificate eligibility
- `GET /api/certificate/generate` - Generate certificate

## 🎓 Certificate Eligibility

Students can earn certificates for their current semester if they meet:
- **Average Score**: ≥ 40%
- **Tests Passed**: ≥ 80% of semester tests

## 📚 Modules by Semester

**Semester 2**: Engineering Mathematics II, Physics, Programming in C, Digital Electronics, Engineering Graphics

**Semester 3**: Data Structures, Discrete Mathematics, Computer Organization, OOP, DBMS

**Semester 4**: Operating Systems, Algorithms, Computer Networks, Software Engineering, Web Technologies

**Semester 5**: Theory of Computation, Compiler Design, AI, Computer Graphics, Cryptography

**Semester 6**: Machine Learning, Cloud Computing, Big Data Analytics, IoT, Cyber Security

## 🔧 Configuration

### Backend Configuration
Edit `backend/src/main/resources/application.properties`:
```properties
server.port=8080
spring.datasource.url=jdbc:h2:mem:studynest
spring.jpa.hibernate.ddl-auto=create-drop
jwt.secret=your-secret-key
```

### Frontend Configuration
API base URL is configured in `frontend/src/services/api.js`

## 📄 License

This project is for educational purposes.

## 👥 Authors

- Deepak Mohan Dey

## 🙏 Acknowledgments

- Spring Boot Documentation
- React Documentation
- React Bootstrap
- GeeksforGeeks for educational content references
