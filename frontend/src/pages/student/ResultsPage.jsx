import { useState, useEffect } from 'react';
import { Container, Card, Table, Badge, Form, Row, Col, Button } from 'react-bootstrap';
import { useLocation } from 'react-router-dom';
import { FaTrophy, FaCheckCircle, FaTimesCircle, FaFilter } from 'react-icons/fa';
import Sidebar from '../../components/Sidebar';
import TopBar from '../../components/TopBar';
import LoadingSpinner from '../../components/LoadingSpinner';
import testService from '../../services/testService';
import './StudentPages.css';

const ResultsPage = ({ user, onLogout }) => {
  const location = useLocation();
  const [results, setResults] = useState([]);
  const [filteredResults, setFilteredResults] = useState([]);
  const [loading, setLoading] = useState(true);
  const [filterSemester, setFilterSemester] = useState('all');
  const [filterStatus, setFilterStatus] = useState('all');

  useEffect(() => {
    fetchResults();
  }, []);

  useEffect(() => {
    applyFilters();
  }, [results, filterSemester, filterStatus]);

  const fetchResults = async () => {
    try {
      const data = await testService.getResults();
      setResults(data);
      
      // Show new result notification if coming from test submission
      if (location.state?.newResult) {
        // You can add a toast notification here
        console.log('New result:', location.state.newResult);
      }
    } catch (error) {
      console.error('Error fetching results:', error);
    } finally {
      setLoading(false);
    }
  };

  const applyFilters = () => {
    let filtered = [...results];

    // Filter by semester
    if (filterSemester !== 'all') {
      filtered = filtered.filter(r => r.semester === parseInt(filterSemester));
    }

    // Filter by status
    if (filterStatus === 'passed') {
      filtered = filtered.filter(r => r.passed);
    } else if (filterStatus === 'failed') {
      filtered = filtered.filter(r => !r.passed);
    }

    // Sort by date (newest first) - default sorting
    filtered.sort((a, b) => {
      // If completedAt exists, use it; otherwise use id for ordering
      if (a.completedAt && b.completedAt) {
        return new Date(b.completedAt) - new Date(a.completedAt);
      }
      return b.id - a.id; // Fallback to id-based sorting
    });

    setFilteredResults(filtered);
  };

  if (loading) return <LoadingSpinner />;

  const totalTests = results.length;
  const passedTests = results.filter(r => r.passed).length;
  const averageScore = totalTests > 0
    ? (results.reduce((sum, r) => sum + r.score, 0) / totalTests).toFixed(1)
    : 0;

  return (
    <div className="d-flex">
      <Sidebar role="STUDENT" onLogout={onLogout} user={user} />
      <TopBar user={user} />
      <div className="flex-grow-1" style={{ marginLeft: '260px', paddingTop: '60px' }}>
        <Container fluid className="p-4" style={{ maxWidth: '1400px' }}>
          <div className="mb-4">
            <h2 className="fw-bold">Test Results</h2>
            <p className="text-muted">Track your performance and progress</p>
          </div>

          {/* Stats Cards */}
          <Row className="g-4 mb-4">
            <Col md={4}>
              <Card className="border-0 shadow-sm">
                <Card.Body className="p-4">
                  <div className="d-flex align-items-center">
                    <div className="stat-icon bg-primary bg-opacity-10 text-primary me-3">
                      <FaTrophy size={24} />
                    </div>
                    <div>
                      <h3 className="fw-bold mb-0">{totalTests}</h3>
                      <p className="text-muted mb-0">Total Tests</p>
                    </div>
                  </div>
                </Card.Body>
              </Card>
            </Col>
            <Col md={4}>
              <Card className="border-0 shadow-sm">
                <Card.Body className="p-4">
                  <div className="d-flex align-items-center">
                    <div className="stat-icon bg-success bg-opacity-10 text-success me-3">
                      <FaCheckCircle size={24} />
                    </div>
                    <div>
                      <h3 className="fw-bold mb-0">{passedTests}</h3>
                      <p className="text-muted mb-0">Tests Passed</p>
                    </div>
                  </div>
                </Card.Body>
              </Card>
            </Col>
            <Col md={4}>
              <Card className="border-0 shadow-sm">
                <Card.Body className="p-4">
                  <div className="d-flex align-items-center">
                    <div className="stat-icon bg-warning bg-opacity-10 text-warning me-3">
                      <FaTrophy size={24} />
                    </div>
                    <div>
                      <h3 className="fw-bold mb-0">{averageScore}%</h3>
                      <p className="text-muted mb-0">Average Score</p>
                    </div>
                  </div>
                </Card.Body>
              </Card>
            </Col>
          </Row>

          {/* Filters */}
          <Card className="border-0 shadow-sm mb-4">
            <Card.Body className="p-4">
              <div className="d-flex align-items-center mb-3">
                <FaFilter className="text-primary me-2" />
                <h6 className="fw-bold mb-0">Filters</h6>
              </div>
              <Row className="g-3">
                <Col md={6}>
                  <Form.Group>
                    <Form.Label className="small text-muted">Semester</Form.Label>
                    <Form.Select
                      value={filterSemester}
                      onChange={(e) => setFilterSemester(e.target.value)}
                    >
                      <option value="all">All Semesters</option>
                      <option value="2">Semester 2</option>
                      <option value="3">Semester 3</option>
                      <option value="4">Semester 4</option>
                      <option value="5">Semester 5</option>
                      <option value="6">Semester 6</option>
                    </Form.Select>
                  </Form.Group>
                </Col>
                <Col md={6}>
                  <Form.Group>
                    <Form.Label className="small text-muted">Status</Form.Label>
                    <Form.Select
                      value={filterStatus}
                      onChange={(e) => setFilterStatus(e.target.value)}
                    >
                      <option value="all">All Results</option>
                      <option value="passed">Passed Only</option>
                      <option value="failed">Failed Only</option>
                    </Form.Select>
                  </Form.Group>
                </Col>
              </Row>
            </Card.Body>
          </Card>

          {/* Results Table */}
          <Card className="border-0 shadow-sm">
            <Card.Body className="p-0">
              {filteredResults.length > 0 ? (
                <div className="table-responsive">
                  <Table hover className="mb-0">
                    <thead className="bg-light">
                      <tr>
                        <th className="p-3">Module</th>
                        <th className="p-3">Semester</th>
                        <th className="p-3">Score</th>
                        <th className="p-3">Correct Answers</th>
                        <th className="p-3">Status</th>
                        <th className="p-3">Date</th>
                      </tr>
                    </thead>
                    <tbody>
                      {filteredResults.map((result) => (
                        <tr key={result.id}>
                          <td className="p-3">
                            <strong>{result.moduleName}</strong>
                          </td>
                          <td className="p-3">
                            <Badge bg="secondary">Sem {result.semester}</Badge>
                          </td>
                          <td className="p-3">
                            <Badge
                              bg={result.score >= 70 ? 'success' : result.score >= 40 ? 'warning' : 'danger'}
                              className="px-3 py-2"
                            >
                              {result.score}%
                            </Badge>
                          </td>
                          <td className="p-3">
                            {result.correctAnswers} / {result.totalQuestions}
                          </td>
                          <td className="p-3">
                            {result.passed ? (
                              <Badge bg="success" className="d-flex align-items-center gap-1" style={{ width: 'fit-content' }}>
                                <FaCheckCircle /> Passed
                              </Badge>
                            ) : (
                              <Badge bg="danger" className="d-flex align-items-center gap-1" style={{ width: 'fit-content' }}>
                                <FaTimesCircle /> Failed
                              </Badge>
                            )}
                          </td>
                          <td className="p-3 text-muted">
                            {result.completedAt ? (
                              new Date(result.completedAt).toLocaleDateString('en-US', {
                                year: 'numeric',
                                month: 'short',
                                day: 'numeric'
                              })
                            ) : (
                              <span className="badge bg-info">Just now</span>
                            )}
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </Table>
                </div>
              ) : (
                <div className="text-center py-5">
                  <FaTrophy size={48} className="text-muted mb-3" />
                  <p className="text-muted">No results found matching your filters</p>
                  <Button
                    variant="link"
                    onClick={() => {
                      setFilterSemester('all');
                      setFilterStatus('all');
                    }}
                  >
                    Clear Filters
                  </Button>
                </div>
              )}
            </Card.Body>
          </Card>
        </Container>
      </div>
    </div>
  );
};

export default ResultsPage;
