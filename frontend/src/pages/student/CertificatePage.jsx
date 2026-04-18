import { useState, useEffect } from 'react';
import { Container, Card, Button, Alert, Row, Col, Badge } from 'react-bootstrap';
import { FaCertificate, FaDownload, FaCheckCircle, FaTimesCircle, FaTrophy } from 'react-icons/fa';
import Navbar from '../../components/Navbar';
import Sidebar from '../../components/Sidebar';
import LoadingSpinner from '../../components/LoadingSpinner';
import certificateService from '../../services/certificateService';
import './StudentPages.css';

const CertificatePage = ({ user, onLogout }) => {
  const [eligibility, setEligibility] = useState(null);
  const [certificate, setCertificate] = useState(null);
  const [loading, setLoading] = useState(true);
  const [generating, setGenerating] = useState(false);
  const [error, setError] = useState('');

  useEffect(() => {
    checkEligibility();
  }, []);

  const checkEligibility = async () => {
    try {
      const data = await certificateService.checkEligibility();
      setEligibility(data);
    } catch (error) {
      console.error('Error checking eligibility:', error);
      setError('Failed to check certificate eligibility');
    } finally {
      setLoading(false);
    }
  };

  const handleGenerateCertificate = async () => {
    setGenerating(true);
    setError('');
    try {
      const blob = await certificateService.generateCertificate();
      
      // Create download link
      const url = window.URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = url;
      link.download = `StudyNest_Certificate_${user.name.replace(/\s+/g, '_')}.pdf`;
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
      window.URL.revokeObjectURL(url);

      setCertificate({ generated: true, date: new Date() });
    } catch (error) {
      console.error('Error generating certificate:', error);
      setError('Failed to generate certificate. Please try again.');
    } finally {
      setGenerating(false);
    }
  };

  if (loading) return <LoadingSpinner />;

  const isEligible = eligibility?.eligible;
  const requirements = [
    {
      label: 'Average Score',
      required: '≥ 40%',
      current: eligibility?.averageScore ? `${eligibility.averageScore.toFixed(1)}%` : 'N/A',
      met: eligibility?.averageScore >= 40
    },
    {
      label: 'Tests Passed',
      required: '≥ 80%',
      current: eligibility?.testsTaken > 0 
        ? `${((eligibility.testsPassed / eligibility.testsTaken) * 100).toFixed(0)}%`
        : '0%',
      met: eligibility?.testsTaken > 0 && (eligibility.testsPassed / eligibility.testsTaken) >= 0.8
    }
  ];

  return (
    <div className="d-flex">
      <Sidebar role="STUDENT" />
      <div className="flex-grow-1">
        <Navbar user={user} onLogout={onLogout} />
        <Container fluid className="p-4">
          <div className="mb-4">
            <h2 className="fw-bold">Certificate</h2>
            <p className="text-muted">Earn your certificate of completion</p>
          </div>

          {error && <Alert variant="danger" dismissible onClose={() => setError('')}>{error}</Alert>}

          <Row className="g-4">
            <Col lg={8}>
              {/* Eligibility Status */}
              <Card className={`border-0 shadow-sm mb-4 ${isEligible ? 'border-success' : ''}`}>
                <Card.Body className="p-4">
                  <div className="d-flex align-items-center mb-4">
                    <div className={`stat-icon ${isEligible ? 'bg-success' : 'bg-warning'} bg-opacity-10 ${isEligible ? 'text-success' : 'text-warning'} me-3`}>
                      <FaCertificate size={32} />
                    </div>
                    <div>
                      <h4 className="fw-bold mb-1">
                        {isEligible ? 'Congratulations! 🎉' : 'Keep Going! 💪'}
                      </h4>
                      <p className="text-muted mb-0">
                        {isEligible 
                          ? 'You are eligible to receive your certificate'
                          : 'Complete the requirements to earn your certificate'}
                      </p>
                    </div>
                  </div>

                  {isEligible ? (
                    <Alert variant="success" className="mb-0">
                      <FaCheckCircle className="me-2" />
                      You have met all the requirements for certificate generation!
                    </Alert>
                  ) : (
                    <Alert variant="warning" className="mb-0">
                      <FaTimesCircle className="me-2" />
                      You need to meet the requirements below to become eligible
                    </Alert>
                  )}
                </Card.Body>
              </Card>

              {/* Requirements */}
              <Card className="border-0 shadow-sm mb-4">
                <Card.Body className="p-4">
                  <h5 className="fw-bold mb-4">Certificate Requirements</h5>
                  
                  {requirements.map((req, index) => (
                    <div key={index} className="mb-4">
                      <div className="d-flex justify-content-between align-items-center mb-2">
                        <div className="d-flex align-items-center">
                          {req.met ? (
                            <FaCheckCircle className="text-success me-2" size={20} />
                          ) : (
                            <FaTimesCircle className="text-danger me-2" size={20} />
                          )}
                          <strong>{req.label}</strong>
                        </div>
                        <Badge bg={req.met ? 'success' : 'secondary'}>
                          {req.met ? 'Met' : 'Not Met'}
                        </Badge>
                      </div>
                      <div className="d-flex justify-content-between text-muted small">
                        <span>Required: {req.required}</span>
                        <span>Current: <strong>{req.current}</strong></span>
                      </div>
                      {index < requirements.length - 1 && <hr className="my-3" />}
                    </div>
                  ))}
                </Card.Body>
              </Card>

              {/* Statistics */}
              <Card className="border-0 shadow-sm">
                <Card.Body className="p-4">
                  <h5 className="fw-bold mb-4">Your Statistics</h5>
                  <Row className="g-3">
                    <Col md={6}>
                      <div className="p-3 bg-light rounded">
                        <div className="text-muted small mb-1">Total Tests Taken</div>
                        <h4 className="fw-bold mb-0">{eligibility?.testsTaken || 0}</h4>
                      </div>
                    </Col>
                    <Col md={6}>
                      <div className="p-3 bg-light rounded">
                        <div className="text-muted small mb-1">Tests Passed</div>
                        <h4 className="fw-bold mb-0">{eligibility?.testsPassed || 0}</h4>
                      </div>
                    </Col>
                    <Col md={6}>
                      <div className="p-3 bg-light rounded">
                        <div className="text-muted small mb-1">Average Score</div>
                        <h4 className="fw-bold mb-0">
                          {eligibility?.averageScore ? `${eligibility.averageScore.toFixed(1)}%` : 'N/A'}
                        </h4>
                      </div>
                    </Col>
                    <Col md={6}>
                      <div className="p-3 bg-light rounded">
                        <div className="text-muted small mb-1">Pass Rate</div>
                        <h4 className="fw-bold mb-0">
                          {eligibility?.testsTaken > 0 
                            ? `${((eligibility.testsPassed / eligibility.testsTaken) * 100).toFixed(0)}%`
                            : '0%'}
                        </h4>
                      </div>
                    </Col>
                  </Row>
                </Card.Body>
              </Card>
            </Col>

            <Col lg={4}>
              {/* Certificate Preview/Action */}
              <Card className="border-0 shadow-sm sticky-top" style={{ top: '20px' }}>
                <Card.Body className="p-4 text-center">
                  <div className="mb-4">
                    <FaTrophy size={64} className={isEligible ? 'text-warning' : 'text-muted'} />
                  </div>
                  
                  <h5 className="fw-bold mb-3">Certificate of Completion</h5>
                  <p className="text-muted mb-4">
                    {isEligible 
                      ? 'Download your official certificate as proof of your achievement'
                      : 'Complete the requirements to unlock your certificate'}
                  </p>

                  {isEligible ? (
                    <>
                      <Button
                        variant="success"
                        size="lg"
                        className="w-100 mb-3"
                        onClick={handleGenerateCertificate}
                        disabled={generating}
                      >
                        <FaDownload className="me-2" />
                        {generating ? 'Generating...' : 'Download Certificate'}
                      </Button>
                      {certificate?.generated && (
                        <Alert variant="success" className="small mb-0">
                          Certificate downloaded successfully!
                        </Alert>
                      )}
                    </>
                  ) : (
                    <Button variant="secondary" size="lg" className="w-100" disabled>
                      <FaCertificate className="me-2" />
                      Not Eligible Yet
                    </Button>
                  )}

                  <hr className="my-4" />

                  <div className="text-start">
                    <h6 className="fw-bold mb-3">What's Included:</h6>
                    <ul className="small text-muted ps-3">
                      <li>Official StudyNest certificate</li>
                      <li>Your name and achievement details</li>
                      <li>Completion date</li>
                      <li>Digital signature</li>
                      <li>PDF format for easy sharing</li>
                    </ul>
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

export default CertificatePage;
