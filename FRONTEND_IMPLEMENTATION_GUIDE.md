# StudyNest Frontend Implementation Guide

## ✅ Already Created Files

### Core Files
- ✅ `src/App.jsx` - Main app with routing
- ✅ `src/App.css` - Global styles
- ✅ `src/index.css` - Base styles
- ✅ `src/main.jsx` - Entry point

### Services (API Integration)
- ✅ `src/services/api.js` - Axios instance with interceptors
- ✅ `src/services/authService.js` - Authentication APIs
- ✅ `src/services/moduleService.js` - Module APIs
- ✅ `src/services/questionService.js` - Question APIs
- ✅ `src/services/testService.js` - Test submission APIs
- ✅ `src/services/studentService.js` - Student profile APIs
- ✅ `src/services/certificateService.js` - Certificate APIs

### Components
- ✅ `src/components/ProtectedRoute.jsx` - Route protection
- ✅ `src/components/Navbar.jsx` - Navigation bar
- ✅ `src/components/Sidebar.jsx` - Sidebar navigation
- ✅ `src/components/Sidebar.css` - Sidebar styles
- ✅ `src/components/LoadingSpinner.jsx` - Loading component
- ✅ `src/components/ModuleCard.jsx` - Module display card
- ✅ `src/components/ModuleCard.css` - Module card styles

### Pages
- ✅ `src/pages/LandingPage.jsx` - Home page
- ✅ `src/pages/LandingPage.css` - Landing page styles

## 📝 Remaining Files to Create

### 1. Login Page
**File:** `src/pages/LoginPage.jsx`

```jsx
import { useState } from 'react';
import { Container, Row, Col, Card, Form, Button, Alert } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
import { FaEnvelope, FaLock } from 'react-icons/fa';
import authService from '../services/authService';

const LoginPage = ({ onLogin }) => {
  const [formData, setFormData] = useState({ email: '', password: '' });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setLoading(true);

    try {
      const response = await authService.login(formData);
      onLogin(response);
      navigate(response.role === 'STUDENT' ? '/student/dashboard' : '/tutor/dashboard');
    } catch (err) {
      setError(err.response?.data?.message || 'Login failed');
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container className="py-5">
      <Row className="justify-content-center">
        <Col md={6} lg={5}>
          <Card className="shadow-lg border-0">
            <Card.Body className="p-5">
              <h2 className="text-center mb-4">Login to StudyNest</h2>
              {error && <Alert variant="danger">{error}</Alert>}
              <Form onSubmit={handleSubmit}>
                <Form.Group className="mb-3">
                  <Form.Label><FaEnvelope className="me-2" />Email</Form.Label>
                  <Form.Control
                    type="email"
                    placeholder="Enter email"
                    value={formData.email}
                    onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                    required
                  />
                </Form.Group>
                <Form.Group className="mb-4">
                  <Form.Label><FaLock className="me-2" />Password</Form.Label>
                  <Form.Control
                    type="password"
                    placeholder="Enter password"
                    value={formData.password}
                    onChange={(e) => setFormData({ ...formData, password: e.target.value })}
                    required
                  />
                </Form.Group>
                <Button variant="primary" type="submit" className="w-100 mb-3" disabled={loading}>
                  {loading ? 'Logging in...' : 'Login'}
                </Button>
              </Form>
              <div className="text-center">
                <p>Don't have an account?</p>
                <Link to="/register/student" className="btn btn-outline-primary me-2">Student Register</Link>
                <Link to="/register/tutor" className="btn btn-outline-secondary">Tutor Register</Link>
              </div>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default LoginPage;
```

### 2. Student Registration Page
**File:** `src/pages/StudentRegisterPage.jsx`

Similar structure to LoginPage but with more fields (name, rollNo, prn, semester, email, password, mobile)

### 3. Student Dashboard
**File:** `src/pages/student/StudentDashboard.jsx`

- Show welcome message
- Display semester modules
- Show recent test results
- Quick stats (tests taken, average score, etc.)

### 4. Modules Page
**File:** `src/pages/student/ModulesPage.jsx`

- Semester tabs (2, 3, 4, 5, 6)
- Grid of ModuleCard components
- Filter by semester

### 5. Test Page
**File:** `src/pages/student/TestPage.jsx`

- Display questions one by one or all at once
- Radio buttons for options
- Submit button
- Timer (optional)
- Show result after submission

### 6. Results Page
**File:** `src/pages/student/ResultsPage.jsx`

- Table of all test results
- Show module name, score, percentage, status, date
- Filter/sort options

### 7. Certificate Page
**File:** `src/pages/student/CertificatePage.jsx`

- Check eligibility
- Generate certificate button
- Display certificate details
- Download/Print option

## 🎨 Design Guidelines

### Color Scheme
- Primary: #4a90e2 (Blue)
- Secondary: #50c878 (Green)
- Accent: #ff6b6b (Red)
- Success: #28a745
- Warning: #ffc107
- Danger: #dc3545

### Typography
- Headings: Bold, 1.5-2rem
- Body: 1rem, regular
- Buttons: 0.9rem, semi-bold

### Spacing
- Section padding: 3-5rem
- Card padding: 2rem
- Element margins: 1-2rem

### Components Style
- Border radius: 8-12px
- Box shadow: 0 4px 6px rgba(0,0,0,0.1)
- Hover effects: translateY(-5px)
- Transitions: 0.3s ease

## 📦 Required npm Packages

Already added to package.json:
- react-router-dom
- react-bootstrap
- bootstrap
- axios
- react-icons

## 🚀 Running the Application

1. Install dependencies:
```bash
cd frontend/studynest-frontend
npm install
```

2. Start development server:
```bash
npm run dev
```

3. Access at: http://localhost:5173

## 🔗 API Integration Checklist

- ✅ Axios instance configured
- ✅ JWT token interceptor
- ✅ Error handling interceptor
- ✅ All service files created
- ✅ CORS configured in backend

## 📱 Responsive Design

- Mobile: < 768px
- Tablet: 768px - 1024px
- Desktop: > 1024px

Use Bootstrap's responsive classes:
- `col-12 col-md-6 col-lg-4`
- `d-none d-md-block`
- `flex-column flex-md-row`

## 🎯 Key Features to Implement

1. **Authentication Flow**
   - Login/Register
   - JWT storage
   - Auto-redirect
   - Logout

2. **Module System**
   - List by semester
   - View details
   - Access resources
   - Start test

3. **Testing System**
   - Load questions
   - Submit answers
   - View results
   - Track progress

4. **Certificate System**
   - Check eligibility
   - Generate certificate
   - Display details
   - Download option

## 🐛 Error Handling

- Show user-friendly messages
- Handle network errors
- Validate forms
- Display loading states
- Handle 401 (redirect to login)
- Handle 403 (access denied)
- Handle 404 (not found)

## ✨ Enhancements (Optional)

- Dark mode toggle
- Progress bars
- Animations
- Toast notifications
- Search functionality
- Pagination
- Export results to PDF
- Email certificate
- Social sharing

## 📝 Testing Checklist

- [ ] Register as student
- [ ] Login
- [ ] View modules
- [ ] Take test
- [ ] Submit test
- [ ] View results
- [ ] Generate certificate
- [ ] Logout
- [ ] Register as tutor
- [ ] View modules with answers

## 🎓 Complete Implementation Steps

1. ✅ Setup project structure
2. ✅ Create services
3. ✅ Create components
4. ✅ Create landing page
5. ⏳ Create auth pages (Login, Register)
6. ⏳ Create student pages (Dashboard, Modules, Test, Results, Certificate, Profile)
7. ⏳ Create tutor pages (Dashboard, Modules)
8. ⏳ Test all functionality
9. ⏳ Fix bugs
10. ⏳ Deploy

## 📚 Resources

- React Bootstrap Docs: https://react-bootstrap.github.io/
- React Router Docs: https://reactrouter.com/
- React Icons: https://react-icons.github.io/react-icons/
- Axios Docs: https://axios-http.com/

---

**Note:** The foundation is complete. Now implement the remaining pages following the same pattern as LandingPage and using the services already created.
