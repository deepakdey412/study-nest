import { useState, useEffect } from 'react';
import { Container, Card, Badge, Button, Accordion } from 'react-bootstrap';
import { useParams, Link } from 'react-router-dom';
import { FaArrowLeft, FaCheckCircle, FaBook } from 'react-icons/fa';
import Navbar from '../../components/Navbar';
import Sidebar from '../../components/Sidebar';
import LoadingSpinner from '../../components/LoadingSpinner';
import questionService from '../../services/questionService';
import moduleService from '../../services/moduleService';
import '../student/StudentPages.css';

const ModuleQuestionsPage = ({ user, onLogout }) => {
  const { moduleId } = useParams();
  const [module, setModule] = useState(null);
  const [questions, setQuestions] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchData();
  }, [moduleId]);

  const fetchData = async () => {
    try {
      const [moduleData, questionsData] = await Promise.all([
        moduleService.getModuleById(moduleId),
        questionService.getQuestionsWithAnswers(moduleId)
      ]);
      setModule(moduleData);
      setQuestions(questionsData);
    } catch (error) {
      console.error('Error fetching data:', error);
    } finally {
      setLoading(false);
    }
  };

  if (loading) return <LoadingSpinner />;

  const getOptionBadge = (option, correctAnswer) => {
    return option === correctAnswer ? 'success' : 'secondary';
  };

  return (
    <div className="d-flex">
      <Sidebar role="TUTOR" />
      <div className="flex-grow-1">
        <Navbar user={user} onLogout={onLogout} />
        <Container fluid className="p-4">
          <Button 
            as={Link} 
            to="/tutor/modules" 
            variant="link" 
            className="mb-3 text-decoration-none"
          >
            <FaArrowLeft className="me-2" />
            Back to Modules
          </Button>

          {/* Module Header */}
          <Card className="border-0 shadow-sm mb-4">
            <Card.Body className="p-4">
              <div className="d-flex align-items-start justify-content-between">
                <div>
                  <Badge bg="primary" className="mb-2">Semester {module?.semester}</Badge>
                  <h2 className="fw-bold mb-2">{module?.name}</h2>
                  <p className="text-muted mb-0">{module?.code}</p>
                </div>
                <div className="text-end">
                  <FaBook size={40} className="text-primary" />
                </div>
              </div>
            </Card.Body>
          </Card>

          {/* Questions List */}
          <Card className="border-0 shadow-sm">
            <Card.Body className="p-4">
              <div className="d-flex justify-content-between align-items-center mb-4">
                <h5 className="fw-bold mb-0">Questions & Answers</h5>
                <Badge bg="info">{questions.length} Questions</Badge>
              </div>

              {questions.length > 0 ? (
                <Accordion defaultActiveKey="0">
                  {questions.map((question, index) => (
                    <Accordion.Item key={question.id} eventKey={index.toString()}>
                      <Accordion.Header>
                        <div className="d-flex align-items-center w-100">
                          <Badge bg="primary" className="me-3">Q{index + 1}</Badge>
                          <span className="fw-semibold">{question.questionText}</span>
                        </div>
                      </Accordion.Header>
                      <Accordion.Body>
                        <div className="mb-3">
                          <h6 className="text-muted mb-3">Options:</h6>
                          <div className="d-grid gap-2">
                            {['option1', 'option2', 'option3', 'option4'].map((optionKey, idx) => (
                              <div
                                key={optionKey}
                                className={`p-3 border rounded ${
                                  optionKey === question.correctAnswer
                                    ? 'border-success bg-success bg-opacity-10'
                                    : 'border-secondary'
                                }`}
                              >
                                <div className="d-flex justify-content-between align-items-center">
                                  <div>
                                    <Badge 
                                      bg={getOptionBadge(optionKey, question.correctAnswer)}
                                      className="me-2"
                                    >
                                      {String.fromCharCode(65 + idx)}
                                    </Badge>
                                    <span>{question[optionKey]}</span>
                                  </div>
                                  {optionKey === question.correctAnswer && (
                                    <FaCheckCircle className="text-success" size={20} />
                                  )}
                                </div>
                              </div>
                            ))}
                          </div>
                        </div>

                        <div className="mt-4 p-3 bg-success bg-opacity-10 border border-success rounded">
                          <div className="d-flex align-items-center">
                            <FaCheckCircle className="text-success me-2" size={20} />
                            <strong className="text-success">Correct Answer: </strong>
                            <span className="ms-2">
                              {question.correctAnswer === 'option1' && 'A'} 
                              {question.correctAnswer === 'option2' && 'B'}
                              {question.correctAnswer === 'option3' && 'C'}
                              {question.correctAnswer === 'option4' && 'D'}
                              {' - '}
                              {question[question.correctAnswer]}
                            </span>
                          </div>
                        </div>
                      </Accordion.Body>
                    </Accordion.Item>
                  ))}
                </Accordion>
              ) : (
                <div className="text-center py-5">
                  <p className="text-muted">No questions available for this module</p>
                </div>
              )}
            </Card.Body>
          </Card>
        </Container>
      </div>
    </div>
  );
};

export default ModuleQuestionsPage;
