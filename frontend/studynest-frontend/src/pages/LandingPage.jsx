import { Container, Row, Col, Button, Card } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { FaGraduationCap, FaBook, FaCertificate, FaChartLine, FaUserGraduate, FaChalkboardTeacher } from 'react-icons/fa';
import './LandingPage.css';

const LandingPage = () => {
  return (
    <div className="landing-page">
      {/* Hero Section */}
      <section className="hero-section text-white text-center py-5">
        <Container>
          <Row className="align-items-center min-vh-100">
            <Col lg={12}>
              <FaGraduationCap size={80} className="mb-4 animate-bounce" />
              <h1 className="display-3 fw-bold mb-4">Welcome to StudyNest</h1>
              <p className="lead mb-5">
                Your Complete Student Learning Platform
                <br />
                Learn, Practice, Test, and Get Certified
              </p>
              <div className="d-flex gap-3 justify-content-center flex-wrap">
                <Button as={Link} to="/register/student" variant="light" size="lg" className="px-5 py-3">
                  <FaUserGraduate className="me-2" />
                  Register as Student
                </Button>
                <Button as={Link} to="/register/tutor" variant="outline-light" size="lg" className="px-5 py-3">
                  <FaChalkboardTeacher className="me-2" />
                  Register as Tutor
                </Button>
              </div>
              <div className="mt-4">
                <Link to="/login" className="text-white text-decoration-none">
                  Already have an account? <strong>Login here</strong>
                </Link>
              </div>
            </Col>
          </Row>
        </Container>
      </section>

      {/* Features Section */}
      <section className="features-section py-5 bg-light">
        <Container>
          <h2 className="text-center mb-5 fw-bold">Why Choose StudyNest?</h2>
          <Row className="g-4">
            <Col md={4}>
              <Card className="feature-card h-100 text-center border-0 shadow-sm">
                <Card.Body className="p-4">
                  <FaBook size={50} className="text-primary mb-3" />
                  <Card.Title className="fw-bold">25 Modules</Card.Title>
                  <Card.Text className="text-muted">
                    Comprehensive study materials across 5 semesters covering all major computer science subjects
                  </Card.Text>
                </Card.Body>
              </Card>
            </Col>
            <Col md={4}>
              <Card className="feature-card h-100 text-center border-0 shadow-sm">
                <Card.Body className="p-4">
                  <FaChartLine size={50} className="text-success mb-3" />
                  <Card.Title className="fw-bold">250 MCQ Questions</Card.Title>
                  <Card.Text className="text-muted">
                    Practice with 10 questions per module and track your progress with detailed results
                  </Card.Text>
                </Card.Body>
              </Card>
            </Col>
            <Col md={4}>
              <Card className="feature-card h-100 text-center border-0 shadow-sm">
                <Card.Body className="p-4">
                  <FaCertificate size={50} className="text-warning mb-3" />
                  <Card.Title className="fw-bold">Get Certified</Card.Title>
                  <Card.Text className="text-muted">
                    Earn your certificate after completing tests with 40% average and 80% pass rate
                  </Card.Text>
                </Card.Body>
              </Card>
            </Col>
          </Row>
        </Container>
      </section>

      {/* Semesters Section */}
      <section className="semesters-section py-5">
        <Container>
          <h2 className="text-center mb-5 fw-bold">Available Semesters</h2>
          <Row className="g-4">
            {[2, 3, 4, 5, 6].map((sem) => (
              <Col key={sem} md={4} lg={2}>
                <Card className="semester-card text-center border-primary">
                  <Card.Body>
                    <h3 className="text-primary fw-bold">Semester {sem}</h3>
                    <p className="text-muted mb-0">5 Subjects</p>
                  </Card.Body>
                </Card>
              </Col>
            ))}
          </Row>
        </Container>
      </section>

      {/* CTA Section */}
      <section className="cta-section text-white text-center py-5">
        <Container>
          <h2 className="display-5 fw-bold mb-4">Ready to Start Learning?</h2>
          <p className="lead mb-4">Join thousands of students already learning on StudyNest</p>
          <Button as={Link} to="/register/student" variant="light" size="lg" className="px-5 py-3">
            Get Started Now
          </Button>
        </Container>
      </section>

      {/* Footer */}
      <footer className="bg-dark text-white text-center py-4">
        <Container>
          <p className="mb-0">&copy; 2026 StudyNest. All rights reserved.</p>
        </Container>
      </footer>
    </div>
  );
};

export default LandingPage;
