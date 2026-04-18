import { useState, useEffect } from 'react';
import { Container, Row, Col, Card, Button, Badge } from 'react-bootstrap';
import { useParams, useNavigate, Link } from 'react-router-dom';
import { FaBook, FaClock, FaQuestionCircle, FaArrowLeft, FaPlay } from 'react-icons/fa';
import Navbar from '../../components/Navbar';
import Sidebar from '../../components/Sidebar';
import LoadingSpinner from '../../components/LoadingSpinner';
import moduleService from '../../services/moduleService';
import './StudentPages.css';

const ModuleDetailPage = ({ user, onLogout }) => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [module, setModule] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchModuleDetails();
  }, [id]);

  const fetchModuleDetails = async () => {
    try {
      const data = await moduleService.getModuleById(id);
      setModule(data);
    } catch (error) {
      console.error('Error fetching module details:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleStartTest = () => {
    navigate(`/student/test/${id}`);
  };

  if (loading) return <LoadingSpinner />;

  if (!module) {
    return (
      <div className="d-flex">
        <Sidebar role="STUDENT" />
        <div className="flex-grow-1">
          <Navbar user={user} onLogout={onLogout} />
          <Container className="py-5 text-center">
            <h3>Module not found</h3>
            <Button as={Link} to="/student/modules" variant="primary" className="mt-3">
              Back to Modules
            </Button>
          </Container>
        </div>
      </div>
    );
  }

  return (
    <div className="d-flex">
      <Sidebar role="STUDENT" />
      <div className="flex-grow-1">
        <Navbar user={user} onLogout={onLogout} />
        <Container fluid className="p-4">
          <Button 
            as={Link} 
            to="/student/modules" 
            variant="link" 
            className="mb-3 text-decoration-none"
          >
            <FaArrowLeft className="me-2" />
            Back to Modules
          </Button>

          <Row className="g-4">
            <Col lg={8}>
              <Card className="border-0 shadow-sm mb-4">
                <Card.Body className="p-4">
                  <div className="mb-3">
                    <Badge bg="primary" className="mb-2">Semester {module.semester}</Badge>
                    <h2 className="fw-bold mb-3">{module.name}</h2>
                    <p className="text-muted lead">{module.description}</p>
                  </div>

                  <hr />

                  <div className="module-info">
                    <Row className="g-3">
                      <Col md={4}>
                        <div className="d-flex align-items-center">
                          <FaBook className="text-primary me-2" size={20} />
                          <div>
                            <small className="text-muted d-block">Subject Code</small>
                            <strong>{module.code}</strong>
                          </div>
                        </div>
                      </Col>
                      <Col md={4}>
                        <div className="d-flex align-items-center">
                          <FaQuestionCircle className="text-success me-2" size={20} />
                          <div>
                            <small className="text-muted d-block">Total Questions</small>
                            <strong>10 MCQs</strong>
                          </div>
                        </div>
                      </Col>
                      <Col md={4}>
                        <div className="d-flex align-items-center">
                          <FaClock className="text-warning me-2" size={20} />
                          <div>
                            <small className="text-muted d-block">Duration</small>
                            <strong>15 minutes</strong>
                          </div>
                        </div>
                      </Col>
                    </Row>
                  </div>
                </Card.Body>
              </Card>

              <Card className="border-0 shadow-sm">
                <Card.Body className="p-4">
                  <h5 className="fw-bold mb-3">About This Module</h5>
                  <p className="text-muted mb-4">
                    This module contains comprehensive learning material and assessment questions 
                    designed to test your understanding of {module.name}. Complete the test to 
                    evaluate your knowledge and track your progress.
                  </p>

                  <h5 className="fw-bold mb-3">Learning Outcomes</h5>
                  <ul className="text-muted">
                    <li>Understand core concepts of {module.name}</li>
                    <li>Apply theoretical knowledge to practical scenarios</li>
                    <li>Develop problem-solving skills in this subject area</li>
                    <li>Prepare for advanced topics in upcoming semesters</li>
                  </ul>
                </Card.Body>
              </Card>
            </Col>

            <Col lg={4}>
              <Card className="border-0 shadow-sm sticky-top" style={{ top: '20px' }}>
                <Card.Body className="p-4">
                  <h5 className="fw-bold mb-4">Test Information</h5>
                  
                  <div className="mb-4">
                    <div className="d-flex justify-content-between mb-2">
                      <span className="text-muted">Questions</span>
                      <strong>10</strong>
                    </div>
                    <div className="d-flex justify-content-between mb-2">
                      <span className="text-muted">Duration</span>
                      <strong>15 minutes</strong>
                    </div>
                    <div className="d-flex justify-content-between mb-2">
                      <span className="text-muted">Passing Score</span>
                      <strong>40%</strong>
                    </div>
                    <div className="d-flex justify-content-between mb-2">
                      <span className="text-muted">Question Type</span>
                      <strong>Multiple Choice</strong>
                    </div>
                  </div>

                  <hr />

                  <div className="mb-4">
                    <h6 className="fw-bold mb-3">Instructions</h6>
                    <ul className="small text-muted ps-3">
                      <li>Read each question carefully</li>
                      <li>Select the best answer for each question</li>
                      <li>You can change answers before submitting</li>
                      <li>Submit the test when you're done</li>
                    </ul>
                  </div>

                  <Button 
                    variant="primary" 
                    size="lg" 
                    className="w-100"
                    onClick={handleStartTest}
                  >
                    <FaPlay className="me-2" />
                    Start Test
                  </Button>

                  <p className="text-center text-muted small mt-3 mb-0">
                    Good luck with your test!
                  </p>
                </Card.Body>
              </Card>
            </Col>
          </Row>
        </Container>
      </div>
    </div>
  );
};

export default ModuleDetailPage;
