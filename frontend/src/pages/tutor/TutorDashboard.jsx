import { useState, useEffect } from 'react';
import { Container, Row, Col, Card } from 'react-bootstrap';
import { FaBook, FaUsers, FaChartLine, FaGraduationCap } from 'react-icons/fa';
import Sidebar from '../../components/Sidebar';
import TopBar from '../../components/TopBar';
import LoadingSpinner from '../../components/LoadingSpinner';
import moduleService from '../../services/moduleService';
import '../student/StudentPages.css';

const TutorDashboard = ({ user, onLogout }) => {
  const [modules, setModules] = useState([]);
  const [loading, setLoading] = useState(true);
  const isPending = user?.approvalStatus === 'PENDING';

  useEffect(() => {
    if (!isPending) {
      fetchModules();
    } else {
      setLoading(false);
    }
  }, [isPending]);

  const fetchModules = async () => {
    try {
      const data = await moduleService.getAllModules();
      setModules(data);
    } catch (error) {
      console.error('Error fetching modules:', error);
    } finally {
      setLoading(false);
    }
  };

  if (loading) return <LoadingSpinner />;

  const stats = [
    {
      icon: FaBook,
      title: 'Total Modules',
      value: modules.length,
      color: 'primary'
    },
    {
      icon: FaGraduationCap,
      title: 'Semesters',
      value: '5',
      color: 'success'
    },
    {
      icon: FaUsers,
      title: 'Active Students',
      value: 'N/A',
      color: 'warning'
    },
    {
      icon: FaChartLine,
      title: 'Avg Performance',
      value: 'N/A',
      color: 'info'
    }
  ];

  return (
    <div className="d-flex">
      <Sidebar role="TUTOR" onLogout={onLogout} user={user} />
      <TopBar user={user} />
      <div className="flex-grow-1" style={{ marginLeft: '260px', paddingTop: '60px' }}>
        <Container fluid className="p-4" style={{ maxWidth: '1400px' }}>
          <div className="mb-4">
            <h2 className="fw-bold">Tutor Dashboard</h2>
            <p className="text-muted">Welcome back, {user.name}! 👋</p>
          </div>

          {/* Pending Approval Message */}
          {isPending && (
            <Card className="border-0 shadow-sm mb-4" style={{ backgroundColor: '#FFF3CD', borderLeft: '4px solid #FFC107' }}>
              <Card.Body className="p-4">
                <div className="d-flex align-items-start">
                  <div className="me-3" style={{ fontSize: '3rem' }}>⏳</div>
                  <div>
                    <h4 className="fw-bold mb-2" style={{ color: '#856404' }}>Account Pending Approval</h4>
                    <p className="mb-2" style={{ color: '#856404', fontSize: '1.1rem' }}>
                      Your tutor account is currently under review by our Super Admin team.
                    </p>
                    <p className="mb-0" style={{ color: '#856404' }}>
                      <strong>What's next?</strong> Once approved, you'll have full access to:
                    </p>
                    <ul className="mt-2 mb-0" style={{ color: '#856404' }}>
                      <li>View all modules across semesters</li>
                      <li>Manage and edit module content</li>
                      <li>Track student performance</li>
                      <li>Access tutor dashboard features</li>
                    </ul>
                    <p className="mt-3 mb-0" style={{ color: '#856404' }}>
                      <strong>Please wait for admin approval.</strong> You'll receive access shortly! 🚀
                    </p>
                  </div>
                </div>
              </Card.Body>
            </Card>
          )}

          {!isPending && (
            <>
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
                          <p className="text-muted mb-0">{stat.title}</p>
                        </Card.Body>
                      </Card>
                    </Col>
                  );
                })}
              </Row>
            </>
          )}

          {/* Welcome Card */}
          <Card className="border-0 shadow-sm">
            <Card.Body className="p-5 text-center">
              <FaGraduationCap size={64} className="text-primary mb-4" />
              <h3 className="fw-bold mb-3">Welcome to StudyNest Tutor Portal</h3>
              <p className="text-muted lead mb-4">
                {isPending 
                  ? 'Your account is pending approval. Please wait for admin confirmation.'
                  : 'Manage modules, view student performance, and contribute to the learning experience'
                }
              </p>
              {!isPending && (
                <Row className="g-4 mt-4">
                  <Col md={4}>
                    <div className="p-4 bg-light rounded">
                      <FaBook size={32} className="text-primary mb-3" />
                      <h5 className="fw-bold">Module Management</h5>
                      <p className="text-muted small mb-0">
                        View and manage all course modules across semesters
                      </p>
                    </div>
                  </Col>
                  <Col md={4}>
                    <div className="p-4 bg-light rounded">
                      <FaUsers size={32} className="text-success mb-3" />
                      <h5 className="fw-bold">Student Tracking</h5>
                      <p className="text-muted small mb-0">
                        Monitor student progress and performance
                      </p>
                  </div>
                </Col>
                <Col md={4}>
                  <div className="p-4 bg-light rounded">
                    <FaChartLine size={32} className="text-warning mb-3" />
                    <h5 className="fw-bold">Analytics</h5>
                    <p className="text-muted small mb-0">
                      View detailed analytics and insights
                    </p>
                  </div>
                </Col>
              </Row>
            </Card.Body>
          </Card>
        </Container>
      </div>
    </div>
  );
};

export default TutorDashboard;
