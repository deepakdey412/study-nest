import { Navbar as BSNavbar, Container, Nav, Button } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
import { FaGraduationCap, FaUser, FaSignOutAlt } from 'react-icons/fa';

const Navbar = ({ user, onLogout }) => {
  const navigate = useNavigate();

  const handleLogout = () => {
    onLogout();
    navigate('/');
  };

  return (
    <BSNavbar bg="primary" variant="dark" expand="lg" className="shadow-sm">
      <Container>
        <BSNavbar.Brand as={Link} to={user ? (user.role === 'STUDENT' ? '/student/dashboard' : '/tutor/dashboard') : '/'}>
          <FaGraduationCap className="me-2" size={24} />
          <strong>StudyNest</strong>
        </BSNavbar.Brand>
        <BSNavbar.Toggle aria-controls="basic-navbar-nav" />
        <BSNavbar.Collapse id="basic-navbar-nav">
          <Nav className="ms-auto align-items-center">
            {user ? (
              <>
                <Nav.Link as={Link} to={user.role === 'STUDENT' ? '/student/dashboard' : '/tutor/dashboard'} className="text-white">
                  Dashboard
                </Nav.Link>
                {user.role === 'STUDENT' && (
                  <>
                    <Nav.Link as={Link} to="/student/modules" className="text-white">
                      Modules
                    </Nav.Link>
                    <Nav.Link as={Link} to="/student/results" className="text-white">
                      Results
                    </Nav.Link>
                    <Nav.Link as={Link} to="/student/certificate" className="text-white">
                      Certificate
                    </Nav.Link>
                  </>
                )}
                {user.role === 'TUTOR' && (
                  <Nav.Link as={Link} to="/tutor/modules" className="text-white">
                    Modules
                  </Nav.Link>
                )}
                <Nav.Link as={Link} to={user.role === 'STUDENT' ? '/student/profile' : '#'} className="text-white">
                  <FaUser className="me-1" />
                  {user.name}
                </Nav.Link>
                <Button variant="outline-light" size="sm" onClick={handleLogout} className="ms-2">
                  <FaSignOutAlt className="me-1" />
                  Logout
                </Button>
              </>
            ) : (
              <>
                <Nav.Link as={Link} to="/login" className="text-white">
                  Login
                </Nav.Link>
                <Button as={Link} to="/register/student" variant="outline-light" size="sm" className="ms-2">
                  Register
                </Button>
              </>
            )}
          </Nav>
        </BSNavbar.Collapse>
      </Container>
    </BSNavbar>
  );
};

export default Navbar;
