import { useState, useEffect } from 'react';
import { Container, Card, Button, Form, Alert, ProgressBar, Row, Col } from 'react-bootstrap';
import { useParams, useNavigate } from 'react-router-dom';
import { FaClock, FaCheckCircle } from 'react-icons/fa';
import Sidebar from '../../components/Sidebar';
import TopBar from '../../components/TopBar';
import LoadingSpinner from '../../components/LoadingSpinner';
import questionService from '../../services/questionService';
import testService from '../../services/testService';
import './StudentPages.css';

const TestPage = ({ user, onLogout }) => {
  const { moduleId } = useParams();
  const navigate = useNavigate();
  const [module, setModule] = useState(null);
  const [questions, setQuestions] = useState([]);
  const [answers, setAnswers] = useState({});
  const [currentQuestion, setCurrentQuestion] = useState(0);
  const [loading, setLoading] = useState(true);
  const [submitting, setSubmitting] = useState(false);
  const [error, setError] = useState('');
  const [eligibilityError, setEligibilityError] = useState('');
  const [timeLeft, setTimeLeft] = useState(15 * 60); // 15 minutes in seconds

  useEffect(() => {
    fetchModuleAndQuestions();
  }, [moduleId]);

  // Timer effect
  useEffect(() => {
    if (loading || eligibilityError || questions.length === 0) return;

    const timer = setInterval(() => {
      setTimeLeft((prev) => {
        if (prev <= 1) {
          clearInterval(timer);
          handleSubmit();
          return 0;
        }
        return prev - 1;
      });
    }, 1000);

    return () => clearInterval(timer);
  }, [loading, eligibilityError, questions]);

  const formatTime = (seconds) => {
    const mins = Math.floor(seconds / 60);
    const secs = seconds % 60;
    return `${mins.toString().padStart(2, '0')}:${secs.toString().padStart(2, '0')}`;
  };

  const fetchModuleAndQuestions = async () => {
    try {
      // First fetch module to check semester
      const moduleService = (await import('../../services/moduleService')).default;
      const moduleData = await moduleService.getModuleById(moduleId);
      setModule(moduleData);

      // Check if student is eligible for this semester
      const studentService = (await import('../../services/studentService')).default;
      const profile = await studentService.getProfile();
      
      if (profile.semester < moduleData.semester) {
        setEligibilityError(`You are not eligible for this test. This module is for Semester ${moduleData.semester}, but you are in Semester ${profile.semester}.`);
        setLoading(false);
        return;
      }

      // Fetch questions
      const data = await questionService.getQuestionsByModule(moduleId);
      
      if (!data || data.length === 0) {
        setError('No questions available for this module.');
        setLoading(false);
        return;
      }

      setQuestions(data);
      // Initialize answers object
      const initialAnswers = {};
      data.forEach(q => {
        initialAnswers[q.id] = '';
      });
      setAnswers(initialAnswers);
    } catch (error) {
      console.error('Error fetching data:', error);
      setError(error.response?.data?.message || 'Failed to load questions. Please try again.');
    } finally {
      setLoading(false);
    }
  };

  const handleAnswerChange = (questionId, answer) => {
    setAnswers({ ...answers, [questionId]: answer });
  };

  const handleNext = () => {
    if (currentQuestion < questions.length - 1) {
      setCurrentQuestion(currentQuestion + 1);
    }
  };

  const handlePrevious = () => {
    if (currentQuestion > 0) {
      setCurrentQuestion(currentQuestion - 1);
    }
  };

  const handleSubmit = async () => {
    // Check if all questions are answered
    const unanswered = Object.values(answers).filter(a => !a).length;
    if (unanswered > 0) {
      if (!window.confirm(`You have ${unanswered} unanswered question(s). Do you want to submit anyway?`)) {
        return;
      }
    }

    setSubmitting(true);
    try {
      // Convert answers object to the format backend expects
      // Backend expects: { moduleId: number, answers: { questionId: "selectedOption" } }
      const answersMap = {};
      Object.entries(answers).forEach(([questionId, selectedOption]) => {
        answersMap[questionId] = selectedOption;
      });

      const submission = {
        moduleId: parseInt(moduleId),
        answers: answersMap
      };

      const result = await testService.submitTest(submission);
      navigate('/student/results', { state: { newResult: result } });
    } catch (error) {
      console.error('Error submitting test:', error);
      setError(error.response?.data?.message || 'Failed to submit test. Please try again.');
    } finally {
      setSubmitting(false);
    }
  };

  if (loading) return <LoadingSpinner />;

  // Show eligibility error
  if (eligibilityError) {
    return (
      <div className="d-flex">
        <Sidebar role="STUDENT" onLogout={onLogout} user={user} />
        <TopBar user={user} />
        <div className="flex-grow-1" style={{ marginLeft: '260px', paddingTop: '60px' }}>
          <Container className="py-5" style={{ maxWidth: '1400px' }}>
            <Card className="border-0 shadow-sm">
              <Card.Body className="p-5 text-center">
                <div className="mb-4">
                  <div className="bg-warning bg-opacity-10 rounded-circle d-inline-flex p-4 mb-3">
                    <FaClock size={48} className="text-warning" />
                  </div>
                </div>
                <h3 className="fw-bold mb-3">Not Eligible</h3>
                <Alert variant="warning" className="mb-4">
                  {eligibilityError}
                </Alert>
                <p className="text-muted mb-4">
                  You can only take tests for modules in your current semester or lower semesters.
                </p>
                <Button onClick={() => navigate('/student/modules')} variant="primary" size="lg">
                  Back to Modules
                </Button>
              </Card.Body>
            </Card>
          </Container>
        </div>
      </div>
    );
  }

  if (error && questions.length === 0) {
    return (
      <div className="d-flex">
        <Sidebar role="STUDENT" onLogout={onLogout} user={user} />
        <TopBar user={user} />
        <div className="flex-grow-1" style={{ marginLeft: '260px', paddingTop: '60px' }}>
          <Container className="py-5 text-center" style={{ maxWidth: '1400px' }}>
            <Alert variant="danger">{error}</Alert>
            <Button onClick={() => navigate('/student/modules')} variant="primary">
              Back to Modules
            </Button>
          </Container>
        </div>
      </div>
    );
  }

  const question = questions[currentQuestion];
  const progress = ((currentQuestion + 1) / questions.length) * 100;
  const answeredCount = Object.values(answers).filter(a => a).length;

  return (
    <div className="d-flex">
      <Sidebar role="STUDENT" onLogout={onLogout} user={user} />
      <TopBar user={user} />
      <div className="flex-grow-1" style={{ marginLeft: '260px', paddingTop: '60px' }}>
        <Container fluid className="py-4" style={{ maxWidth: '1400px' }}>
          {error && <Alert variant="danger" dismissible onClose={() => setError('')}>{error}</Alert>}

          <Row className="g-4 test-page-row">
            {/* Main Test Area - Left Side */}
            <Col lg={9}>
              {/* Module Header */}
              <Card className="border-0 shadow-sm mb-4">
                <Card.Body className="p-4 bg-primary text-white">
                  <div className="d-flex justify-content-between align-items-center">
                    <div>
                      <h3 className="fw-bold mb-1">{module?.name}</h3>
                      <p className="mb-0 opacity-75">
                        <span className="badge bg-white text-primary me-2">Semester {module?.semester}</span>
                        <span>10 Questions • 15 Minutes</span>
                      </p>
                    </div>
                    <div className="text-end">
                      <div className="d-flex align-items-center gap-2">
                        <FaClock size={20} className={timeLeft < 60 ? 'text-danger' : ''} />
                        <span className={`fs-5 fw-bold ${timeLeft < 60 ? 'text-danger' : ''}`}>
                          {formatTime(timeLeft)}
                        </span>
                      </div>
                      {timeLeft < 60 && (
                        <small className="text-white opacity-75">Time running out!</small>
                      )}
                    </div>
                  </div>
                </Card.Body>
              </Card>

              {/* Progress Bar */}
              <Card className="border-0 shadow-sm mb-4">
                <Card.Body className="p-3">
                  <div className="d-flex justify-content-between align-items-center mb-2">
                    <span className="fw-semibold">Question {currentQuestion + 1} of {questions.length}</span>
                    <span className="text-muted">
                      <FaCheckCircle className="text-success me-1" />
                      {answeredCount} answered
                    </span>
                  </div>
                  <ProgressBar 
                    now={progress} 
                    variant="success" 
                    style={{ height: '10px' }}
                    className="rounded"
                  />
                </Card.Body>
              </Card>

              {/* Question Card */}
              <Card className="border-0 shadow-sm mb-4">
                <Card.Body className="p-4">
                  <h5 className="fw-bold mb-3 text-dark">{question.questionText}</h5>

                  <div className="d-grid gap-2">
                    {['option1', 'option2', 'option3', 'option4'].map((option, index) => {
                      const isSelected = answers[question.id] === option;
                      return (
                        <div
                          key={option}
                          className={`test-option rounded-3 ${isSelected ? 'selected' : ''}`}
                          onClick={() => handleAnswerChange(question.id, option)}
                        >
                          <div className="d-flex align-items-center gap-3">
                            <div className={`option-badge ${isSelected ? 'selected' : ''}`}>
                              {String.fromCharCode(65 + index)}
                            </div>
                            <span className="option-text">{question[option]}</span>
                          </div>
                        </div>
                      );
                    })}
                  </div>
                </Card.Body>
              </Card>

              {/* Navigation Buttons */}
              <div className="d-flex justify-content-between">
                <Button
                  variant="outline-secondary"
                  onClick={handlePrevious}
                  disabled={currentQuestion === 0}
                  size="lg"
                  className="px-4"
                >
                  ← Previous
                </Button>

                <div>
                  {currentQuestion === questions.length - 1 ? (
                    <Button
                      variant="success"
                      onClick={handleSubmit}
                      disabled={submitting}
                      size="lg"
                      className="px-5"
                    >
                      {submitting ? 'Submitting...' : 'Submit Test'}
                    </Button>
                  ) : (
                    <Button
                      variant="primary"
                      onClick={handleNext}
                      size="lg"
                      className="px-4"
                    >
                      Next →
                    </Button>
                  )}
                </div>
              </div>
            </Col>

            {/* Question Navigator - Right Sidebar */}
            <Col lg={3}>
              <Card className="border-0 shadow-sm sticky-top" style={{ top: '20px' }}>
                <Card.Body className="p-4">
                  <h6 className="fw-bold mb-3">Question Navigator</h6>
                  <div className="question-nav-grid">
                    {questions.map((q, index) => (
                      <button
                        key={q.id}
                        type="button"
                        className={`question-nav-btn ${
                          index === currentQuestion
                            ? 'current'
                            : answers[q.id]
                            ? 'answered'
                            : 'unanswered'
                        }`}
                        onClick={() => setCurrentQuestion(index)}
                      >
                        {index + 1}
                      </button>
                    ))}
                  </div>

                  <hr className="my-4" />

                  <div className="legend">
                    <div className="d-flex align-items-center mb-2">
                      <div className="legend-box bg-primary"></div>
                      <small className="text-muted">Current</small>
                    </div>
                    <div className="d-flex align-items-center mb-2">
                      <div className="legend-box bg-success"></div>
                      <small className="text-muted">Answered</small>
                    </div>
                    <div className="d-flex align-items-center">
                      <div className="legend-box border"></div>
                      <small className="text-muted">Not Answered</small>
                    </div>
                  </div>
                </Card.Body>
              </Card>
            </Col>
          </Row>
        </Container>
      </div>
    </div>
  );
};

export default TestPage;
