import { useState, useEffect } from 'react';
import { Container, Row, Col, Card, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { FaBook, FaClipboardList, FaCertificate, FaTrophy, FaChartLine } from 'react-icons/fa';
import Navbar from '../../components/Navbar';
import Sidebar from '../../components/Sidebar';
import LoadingSpinner from '../../components/LoadingSpinner';
import studentService from '../../services/studentService';
import testService from '../../services/testService';
import './StudentPages.css';

const StudentDashboard = ({ user, onLogout }) => {
  const [profile, setProfile] = useState(null);
  const [recentResults, setRecentResults] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchDashboardData();
  }, []);

  const fetchDashboardData = async () => {
    try {
      const [profileData, resultsData] = await Promise.all([
        studentService.getProfile(),
        testService.getResults()
      ]);
      setProfile(profileData);
      setRecentResults(resultsData.slice(0, 5)); // Get last 5 results
    } catch (error) {
      console.error('Error fetching dashboard data:', error);
    } finally {
      setLoading(false);
    }
  };

  if (loading) return <LoadingSpinner />;

  const stats = [
    {
      icon: FaBook,
      title: 'Total Modules',
      value: '25',
      color: 'primary',
      link: '/student/modules'
    },
    {
      icon: FaClipboardList,
      title: 'Tests Taken',
      value: profile?.testsTaken || 0,
      color: 'success',
      link: '/student/results'
    },
    {
      icon: FaTrophy,
      title: 'Average Score',
      value: profile?.averageScore ? `${profile.averageScore.toFixed(1)}%` : 'N/A',
      color: 'warning',
      link: '/student/results'
    },
    {
      icon: FaCertificate,
      title: 'Certificate',
      value: profile?.certificateEligible ? 'Eligible' : 'Not Yet',
      color: profile?.certificateEligible ? 'success' : 'secondary',
      link: '/student/certificate'
    }
  ];

  return (
    <div className="d-flex">
      <Sidebar role="STUDENT" />
      <div className="flex-grow-1">
        <Navbar user={user} onLogout={onLogout} />
        <Container fluid className="p-4">
          <div className="mb-4">
            <h2 className="fw-bold">Welcome back, {user.name}! 👋</h2>
            <p className="text-muted">
              Here's your learning progress overview
              {profile?.semester && (
                <span className="ms-2">
                  <span className="badge bg-primary">Semester {profile.semester}</span>
                </span>
              )}
            </p>
          </div>

          {/* Stats Cards */}
          <Row className="g-4 mb-4">
            {stats.map((stat, index) => {
              const Icon = stat.icon;
              return (
                <Col key={index} md={6} lg={3}>
                  <Card className="stat-card border-0 shadow-sm h-100">
                    <Card.Body>
                      <div className="d-flex justify-content-between align-items-start mb-3">
                        <div className={`stat-icon bg-${stat.color} bg-opacity-10 text-${stat.color}`}>
                          <Icon size={24} />
                        </div>
                      </div>
                      <h3 className="fw-bold mb-1">{stat.value}</h3>
                      <p className="text-muted mb-3">{stat.title}</p>
                      <Link to={stat.link} className={`text-${stat.color} text-decoration-none fw-semibold`}>
                        View Details →
                      </Link>
                    </Card.Body>
                  </Card>
                </Col>
              );
            })}
          </Row>

          {/* Quick Actions */}
          <Row className="g-4 mb-4">
            <Col md={6}>
              <Card className="border-0 shadow-sm h-100">
                <Card.Body className="p-4">
                  <div className="d-flex align-items-center mb-3">
                    <FaBook className="text-primary me-2" size={24} />
                    <h5 className="fw-bold mb-0">Continue Learning</h5>
                  </div>
                  <p className="text-muted mb-3">
                    Explore modules across 5 semesters and test your knowledge
                  </p>
                  <Button as={Link} to="/student/modules" variant="primary" className="w-100">
                    Browse Modules
                  </Button>
                </Card.Body>
              </Card>
            </Col>

            <Col md={6}>
              <Card className="border-0 shadow-sm h-100">
                <Card.Body className="p-4">
                  <div className="d-flex align-items-center mb-3">
                    <FaChartLine className="text-success me-2" size={24} />
                    <h5 className="fw-bold mb-0">Track Progress</h5>
                  </div>
                  <p className="text-muted mb-3">
                    View your test results and performance analytics
                  </p>
                  <Button as={Link} to="/student/results" variant="success" className="w-100">
                    View Results
                  </Button>
                </Card.Body>
              </Card>
            </Col>
          </Row>

          {/* Recent Results */}
          <Card className="border-0 shadow-sm">
            <Card.Body className="p-4">
              <h5 className="fw-bold mb-4">Recent Test Results</h5>
              {recentResults.length > 0 ? (
                <div className="table-responsive">
                  <table className="table table-hover">
                    <thead>
                      <tr>
                        <th>Module</th>
                        <th>Score</th>
                        <th>Status</th>
                        <th>Date</th>
                      </tr>
                    </thead>
                    <tbody>
                      {recentResults.map((result) => (
                        <tr key={result.id}>
                          <td className="fw-semibold">{result.moduleName}</td>
                          <td>
                            <span className={`badge ${result.score >= 40 ? 'bg-success' : 'bg-danger'}`}>
                              {result.score}%
                            </span>
                          </td>
                          <td>
                            <span className={`badge ${result.passed ? 'bg-success' : 'bg-danger'}`}>
                              {result.passed ? 'Passed' : 'Failed'}
                            </span>
                          </td>
                          <td className="text-muted">
                            {new Date(result.testDate).toLocaleDateString()}
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              ) : (
                <div className="text-center py-5">
                  <FaClipboardList size={48} className="text-muted mb-3" />
                  <p className="text-muted">No test results yet. Start taking tests to see your progress!</p>
                  <Button as={Link} to="/student/modules" variant="primary" className="mt-2">
                    Start Learning
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

export default StudentDashboard;
