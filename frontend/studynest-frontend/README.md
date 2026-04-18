# StudyNest Frontend - React + Vite + React Bootstrap

Complete Student Learning Platform Frontend

## 🚀 Quick Start

### 1. Install Dependencies
```bash
npm install
```

### 2. Start Development Server
```bash
npm run dev
```

### 3. Access Application
Open http://localhost:5173 in your browser

## 📦 Installed Packages

- **react** & **react-dom** - Core React
- **react-router-dom** - Routing
- **react-bootstrap** & **bootstrap** - UI Components
- **axios** - HTTP Client
- **react-icons** - Icons

## 📁 Project Structure

```
src/
├── components/          # Reusable components
│   ├── Navbar.jsx
│   ├── Sidebar.jsx
│   ├── LoadingSpinner.jsx
│   ├── ModuleCard.jsx
│   └── ProtectedRoute.jsx
├── pages/              # Page components
│   ├── LandingPage.jsx
│   ├── LoginPage.jsx
│   ├── StudentRegisterPage.jsx
│   ├── TutorRegisterPage.jsx
│   └── student/
│       ├── StudentDashboard.jsx
│       ├── ModulesPage.jsx
│       ├── ModuleDetailPage.jsx
│       ├── TestPage.jsx
│       ├── ResultsPage.jsx
│       ├── CertificatePage.jsx
│       └── ProfilePage.jsx
├── services/           # API services
│   ├── api.js
│   ├── authService.js
│   ├── moduleService.js
│   ├── questionService.js
│   ├── testService.js
│   ├── studentService.js
│   └── certificateService.js
├── App.jsx            # Main app component
├── App.css            # Global styles
├── main.jsx           # Entry point
└── index.css          # Base styles
```

## ✅ Completed Features

### Core Setup
- ✅ Vite + React configuration
- ✅ React Router setup
- ✅ React Bootstrap integration
- ✅ Axios configuration with interceptors
- ✅ JWT token management
- ✅ Protected routes

### Components
- ✅ Responsive Navbar
- ✅ Sidebar navigation
- ✅ Loading spinner
- ✅ Module cards
- ✅ Route protection

### Pages
- ✅ Landing page with hero section
- ✅ Login page
- ✅ Student registration page
- ⏳ Tutor registration page (similar to student)
- ⏳ Student dashboard
- ⏳ Modules listing page
- ⏳ Module detail page
- ⏳ Test taking page
- ⏳ Results page
- ⏳ Certificate page
- ⏳ Profile page

### API Integration
- ✅ Authentication APIs
- ✅ Module APIs
- ✅ Question APIs
- ✅ Test submission APIs
- ✅ Student profile APIs
- ✅ Certificate APIs

## 🎨 Design System

### Colors
```css
--primary-color: #4a90e2;    /* Blue */
--secondary-color: #50c878;  /* Green */
--accent-color: #ff6b6b;     /* Red */
--success-color: #28a745;
--warning-color: #ffc107;
--danger-color: #dc3545;
```

### Typography
- Headings: Bold, Segoe UI
- Body: Regular, 1rem
- Buttons: Semi-bold, 0.9-1rem

## 📝 Remaining Implementation

### Priority 1: Core Student Pages

#### 1. Tutor Registration Page
Create `src/pages/TutorRegisterPage.jsx` similar to StudentRegisterPage but with fields:
- name
- mobileNo
- email
- password

#### 2. Student Dashboard
Create `src/pages/student/StudentDashboard.jsx`:
- Welcome message with user name
- Quick stats cards (tests taken, average score, modules completed)
- Recent test results
- Quick access to modules by semester
- Progress chart/graph

#### 3. Modules Page
Create `src/pages/student/ModulesPage.jsx`:
- Semester tabs (2, 3, 4, 5, 6)
- Grid of ModuleCard components
- Filter by semester
- Search functionality

#### 4. Module Detail Page
Create `src/pages/student/ModuleDetailPage.jsx`:
- Module information
- Resources (PDF, Video, External Links)
- Start Test button
- Previous test results for this module

#### 5. Test Page
Create `src/pages/student/TestPage.jsx`:
- Load questions from API
- Display questions with radio button options
- Navigation between questions
- Submit test button
- Confirmation dialog
- Show result after submission

#### 6. Results Page
Create `src/pages/student/ResultsPage.jsx`:
- Table of all test results
- Columns: Module, Score, Total, Percentage, Status, Date
- Filter by status (PASS/FAIL)
- Sort by date/score
- View details button

#### 7. Certificate Page
Create `src/pages/student/CertificatePage.jsx`:
- Check eligibility status
- Show requirements (40% avg, 80% pass rate)
- Generate certificate button
- Display certificate details
- Download/Print functionality

#### 8. Profile Page
Create `src/pages/student/ProfilePage.jsx`:
- Display student information
- Edit profile form
- Change password option

### Priority 2: Tutor Pages

#### 1. Tutor Dashboard
Create `src/pages/tutor/TutorDashboard.jsx`:
- Welcome message
- Statistics overview
- Quick access to modules

#### 2. Tutor Modules Page
Create `src/pages/tutor/TutorModulesPage.jsx`:
- List all modules
- View questions with answers
- Module statistics

## 🔧 Implementation Guide

### Example: Creating Student Dashboard

```jsx
// src/pages/student/StudentDashboard.jsx
import { useState, useEffect } from 'react';
import { Container, Row, Col, Card } from 'react-bootstrap';
import Navbar from '../../components/Navbar';
import Sidebar from '../../components/Sidebar';
import LoadingSpinner from '../../components/LoadingSpinner';
import studentService from '../../services/studentService';
import testService from '../../services/testService';

const StudentDashboard = ({ user, onLogout }) => {
  const [profile, setProfile] = useState(null);
  const [results, setResults] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const [profileData, resultsData] = await Promise.all([
        studentService.getProfile(),
        testService.getResults()
      ]);
      setProfile(profileData);
      setResults(resultsData);
    } catch (error) {
      console.error('Error fetching data:', error);
    } finally {
      setLoading(false);
    }
  };

  if (loading) return <LoadingSpinner />;

  const totalTests = results.length;
  const passedTests = results.filter(r => r.status === 'PASS').length;
  const avgScore = results.length > 0 
    ? (results.reduce((sum, r) => sum + r.percentage, 0) / results.length).toFixed(2)
    : 0;

  return (
    <>
      <Navbar user={user} onLogout={onLogout} />
      <div className="d-flex">
        <Sidebar role="STUDENT" />
        <Container fluid className="p-4">
          <h2 className="mb-4">Welcome, {profile?.name}!</h2>
          
          <Row className="g-4 mb-4">
            <Col md={3}>
              <Card className="text-center">
                <Card.Body>
                  <h3>{totalTests}</h3>
                  <p>Tests Taken</p>
                </Card.Body>
              </Card>
            </Col>
            <Col md={3}>
              <Card className="text-center">
                <Card.Body>
                  <h3>{passedTests}</h3>
                  <p>Tests Passed</p>
                </Card.Body>
              </Card>
            </Col>
            <Col md={3}>
              <Card className="text-center">
                <Card.Body>
                  <h3>{avgScore}%</h3>
                  <p>Average Score</p>
                </Card.Body>
              </Card>
            </Col>
            <Col md={3}>
              <Card className="text-center">
                <Card.Body>
                  <h3>Sem {profile?.semester}</h3>
                  <p>Current Semester</p>
                </Card.Body>
              </Card>
            </Col>
          </Row>

          {/* Add more sections: Recent results, Quick access to modules, etc. */}
        </Container>
      </div>
    </>
  );
};

export default StudentDashboard;
```

## 🧪 Testing Workflow

1. **Register as Student**
   - Go to /register/student
   - Fill form and submit
   - Should redirect to /student/dashboard

2. **Login**
   - Go to /login
   - Enter credentials
   - Should redirect based on role

3. **View Modules**
   - Navigate to Modules page
   - Select semester
   - View module cards

4. **Take Test**
   - Click "Start Test" on a module
   - Answer questions
   - Submit test
   - View result

5. **View Results**
   - Navigate to Results page
   - See all test results

6. **Generate Certificate**
   - Navigate to Certificate page
   - Check eligibility
   - Generate certificate

## 🐛 Common Issues & Solutions

### Issue: CORS Error
**Solution:** Backend CORS is already configured. Make sure backend is running on port 8080.

### Issue: 401 Unauthorized
**Solution:** Token might be expired. Logout and login again.

### Issue: Module not loading
**Solution:** Check if backend has data seeded. Run backend with data.sql and questions.sql.

### Issue: npm install fails
**Solution:** Delete node_modules and package-lock.json, then run npm install again.

## 📚 Resources

- [React Bootstrap Docs](https://react-bootstrap.github.io/)
- [React Router Docs](https://reactrouter.com/)
- [Axios Docs](https://axios-http.com/)
- [React Icons](https://react-icons.github.io/react-icons/)

## 🎯 Next Steps

1. Complete remaining student pages
2. Implement tutor pages
3. Add form validation
4. Add toast notifications
5. Implement dark mode
6. Add animations
7. Optimize performance
8. Add unit tests
9. Deploy to production

## 📞 Support

For issues or questions, refer to:
- Backend API Documentation: `BACKEND_API_DOCUMENTATION.txt`
- Frontend Implementation Guide: `FRONTEND_IMPLEMENTATION_GUIDE.md`

---

**Happy Coding! 🚀**
