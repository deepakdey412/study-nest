import { useState, useEffect } from 'react';
import { Container, Card, Button, Form, Alert, ProgressBar } from 'react-bootstrap';
import { useParams, useNavigate } from 'react-router-dom';
import { FaClock, FaCheckCircle } from 'react-icons/fa';
import Navbar from '../../components/Navbar';
import Sidebar from '../../components/Sidebar';
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

  useEffect(() => {
    fetchModuleAndQuestions();
  }, [moduleId]);

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
        <Sidebar role="STUDENT" />
        <div className="flex-grow-1">
          <Navbar user={user} onLogout={onLogout} />
          <Container className="py-5">
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
        <Sidebar role="STUDENT" />
        <div className="flex-grow-1">
          <Navbar user={user} onLogout={onLogout} />
          <Container className="py-5 text-center">
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
      <Sidebar role="STUDENT" />
      <div className="flex-grow-1">
        <Navbar user={user} onLogout={onLogout} />
        <Container className="py-4">
          {error && <Alert variant="danger" dismissible onClose={() => setError('')}>{error}</Alert>}

          {/* Progress Header */}
          <Card className="border-0 shadow-sm mb-4">
            <Card.Body className="p-4">
              <div className="d-flex justify-content-between align-items-center mb-3">
                <div>
                  <h5 className="fw-bold mb-1">Question {currentQuestion + 1} of {questions.length}</h5>
                  <p className="text-muted mb-0">
                    <FaCheckCircle className="text-success me-1" />
                    {answeredCount} answered
                  </p>
                </div>
                <div className="text-end">
                  <FaClock className="text-warning me-2" />
                  <span className="text-muted">15:00</span>
                </div>
              </div>
              <ProgressBar now={progress} variant="primary" style={{ height: '8px' }} />
            </Card.Body>
          </Card>

          {/* Question Card */}
          <Card className="border-0 shadow-sm mb-4">
            <Card.Body className="p-4">
              <h4 className="fw-bold mb-4">{question.questionText}</h4>

              <div className="d-grid gap-3">
                {['option1', 'option2', 'option3', 'option4'].map((option, index) => (
                  <div
                    key={option}
                    className={`option-card p-3 border rounded ${
                      answers[question.id] === option ? 'selected' : ''
                    }`}
                    onClick={() => handleAnswerChange(question.id, option)}
                    style={{ cursor: 'pointer' }}
                  >
                    <Form.Check
                      type="radio"
                      id={`${question.id}-${option}`}
                      name={`question-${question.id}`}
                      checked={answers[question.id] === option}
                      onChange={() => {}}
                      label={
                        <span className="ms-2 d-flex align-items-center">
                          <strong className="me-2">{String.fromCharCode(65 + index)}.</strong>
                          <span>{question[option]}</span>
                        </span>
                      }
                      style={{ pointerEvents: 'none' }}
                    />
                  </div>
                ))}
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
                >
                  Next →
                </Button>
              )}
            </div>
          </div>

          {/* Question Navigator */}
          <Card className="border-0 shadow-sm mt-4">
            <Card.Body className="p-4">
              <h6 className="fw-bold mb-3">Question Navigator</h6>
              <div className="d-flex flex-wrap gap-2">
                {questions.map((q, index) => (
                  <Button
                    key={q.id}
                    variant={
                      index === currentQuestion
                        ? 'primary'
                        : answers[q.id]
                        ? 'success'
                        : 'outline-secondary'
                    }
                    size="sm"
                    onClick={() => setCurrentQuestion(index)}
                    style={{ width: '45px', height: '45px' }}
                  >
                    {index + 1}
                  </Button>
                ))}
              </div>
            </Card.Body>
          </Card>
        </Container>
      </div>
    </div>
  );
};

export default TestPage;
