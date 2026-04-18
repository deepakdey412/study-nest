import { useState, useEffect } from 'react';
import { Container, Card, Form, Button, Alert, Row, Col } from 'react-bootstrap';
import { FaUser, FaEnvelope, FaPhone, FaIdCard, FaEdit, FaSave, FaTimes } from 'react-icons/fa';
import Sidebar from '../../components/Sidebar';
import TopBar from '../../components/TopBar';
import LoadingSpinner from '../../components/LoadingSpinner';
import studentService from '../../services/studentService';
import './StudentPages.css';

const ProfilePage = ({ user, onLogout }) => {
  const [profile, setProfile] = useState(null);
  const [editMode, setEditMode] = useState(false);
  const [formData, setFormData] = useState({
    name: '',
    mobileNo: '',
    email: '',
    enrollmentNo: '',
    rollNo: '',
    prn: '',
    semester: ''
  });
  const [loading, setLoading] = useState(true);
  const [saving, setSaving] = useState(false);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  useEffect(() => {
    fetchProfile();
  }, []);

  const fetchProfile = async () => {
    try {
      const data = await studentService.getProfile();
      setProfile(data);
      setFormData({
        name: data.name,
        mobileNo: data.mobile || '',
        email: data.email,
        enrollmentNo: data.enrollmentNo || '',
        rollNo: data.rollNo || '',
        prn: data.prn || '',
        semester: data.semester || ''
      });
    } catch (error) {
      console.error('Error fetching profile:', error);
      setError('Failed to load profile');
    } finally {
      setLoading(false);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleEdit = () => {
    setEditMode(true);
    setError('');
    setSuccess('');
  };

  const handleCancel = () => {
    setEditMode(false);
    setFormData({
      name: profile.name,
      mobileNo: profile.mobile || '',
      email: profile.email,
      enrollmentNo: profile.enrollmentNo || '',
      rollNo: profile.rollNo || '',
      prn: profile.prn || '',
      semester: profile.semester || ''
    });
    setError('');
  };

  const handleSave = async () => {
    setError('');
    setSuccess('');

    // Validation
    if (formData.mobileNo && formData.mobileNo.length !== 10) {
      setError('Mobile number must be 10 digits');
      return;
    }

    setSaving(true);
    try {
      // Send only editable fields
      const updateData = {
        name: formData.name,
        mobile: formData.mobileNo,
        email: formData.email,
        rollNo: formData.rollNo,
        semester: parseInt(formData.semester)
      };
      
      const updated = await studentService.updateProfile(updateData);
      setProfile(updated);
      setEditMode(false);
      setSuccess('Profile updated successfully!');
      
      // Update user in localStorage
      const userData = JSON.parse(localStorage.getItem('user'));
      userData.name = updated.name;
      localStorage.setItem('user', JSON.stringify(userData));
    } catch (error) {
      console.error('Error updating profile:', error);
      setError(error.response?.data?.message || 'Failed to update profile');
    } finally {
      setSaving(false);
    }
  };

  if (loading) return <LoadingSpinner />;

  return (
    <div className="d-flex">
      <Sidebar role="STUDENT" onLogout={onLogout} user={user} />
      <TopBar user={user} />
      <div className="flex-grow-1" style={{ marginLeft: '260px', paddingTop: '60px' }}>
        <Container fluid className="p-4" style={{ maxWidth: '1400px' }}>
          <div className="mb-4">
            <h2 className="fw-bold">My Profile</h2>
            <p className="text-muted">View and manage your account information</p>
          </div>

          {error && <Alert variant="danger" dismissible onClose={() => setError('')}>{error}</Alert>}
          {success && <Alert variant="success" dismissible onClose={() => setSuccess('')}>{success}</Alert>}

          <Row className="justify-content-center">
            <Col lg={8}>
              <Card className="border-0 shadow-sm">
                <Card.Body className="p-4">
                  <div className="d-flex justify-content-between align-items-center mb-4">
                    <h5 className="fw-bold mb-0">Personal Information</h5>
                    {!editMode && (
                      <Button variant="outline-primary" onClick={handleEdit}>
                        <FaEdit className="me-2" />
                        Edit Profile
                      </Button>
                    )}
                  </div>

                  <Form>
                    <Row className="g-3">
                      <Col md={6}>
                        <Form.Group>
                          <Form.Label>
                            <FaUser className="me-2 text-muted" />
                            Full Name
                          </Form.Label>
                          <Form.Control
                            type="text"
                            name="name"
                            value={formData.name}
                            onChange={handleChange}
                            disabled={!editMode}
                            required
                          />
                        </Form.Group>
                      </Col>

                      <Col md={6}>
                        <Form.Group>
                          <Form.Label>
                            <FaIdCard className="me-2 text-muted" />
                            Roll Number
                          </Form.Label>
                          <Form.Control
                            type="text"
                            name="rollNo"
                            value={formData.rollNo}
                            onChange={handleChange}
                            disabled={!editMode}
                          />
                        </Form.Group>
                      </Col>

                      <Col md={6}>
                        <Form.Group>
                          <Form.Label>
                            <FaIdCard className="me-2 text-muted" />
                            PRN 
                            <span className="badge bg-secondary ms-2 small">Read Only</span>
                          </Form.Label>
                          <Form.Control
                            type="text"
                            name="prn"
                            value={formData.prn}
                            disabled
                            className="bg-light text-muted"
                            style={{ cursor: 'not-allowed' }}
                          />
                          <Form.Text className="text-warning">
                            <small>⚠️ PRN cannot be modified for security reasons</small>
                          </Form.Text>
                        </Form.Group>
                      </Col>

                      <Col md={6}>
                        <Form.Group>
                          <Form.Label>
                            <FaEnvelope className="me-2 text-muted" />
                            Email Address
                          </Form.Label>
                          <Form.Control
                            type="email"
                            name="email"
                            value={formData.email}
                            onChange={handleChange}
                            disabled={!editMode}
                            required
                          />
                        </Form.Group>
                      </Col>

                      <Col md={6}>
                        <Form.Group>
                          <Form.Label>
                            <FaPhone className="me-2 text-muted" />
                            Mobile Number
                          </Form.Label>
                          <Form.Control
                            type="tel"
                            name="mobileNo"
                            value={formData.mobileNo}
                            onChange={handleChange}
                            disabled={!editMode}
                            pattern="[0-9]{10}"
                            maxLength="10"
                            placeholder="10 digit mobile number"
                          />
                        </Form.Group>
                      </Col>

                      <Col md={6}>
                        <Form.Group>
                          <Form.Label>
                            <FaIdCard className="me-2 text-muted" />
                            Semester
                          </Form.Label>
                          <Form.Select
                            name="semester"
                            value={formData.semester}
                            onChange={handleChange}
                            disabled={!editMode}
                          >
                            <option value="">Select Semester</option>
                            <option value="2">Semester 2</option>
                            <option value="3">Semester 3</option>
                            <option value="4">Semester 4</option>
                            <option value="5">Semester 5</option>
                            <option value="6">Semester 6</option>
                          </Form.Select>
                        </Form.Group>
                      </Col>
                    </Row>

                    {editMode && (
                      <div className="d-flex gap-2 mt-4">
                        <Button
                          variant="primary"
                          onClick={handleSave}
                          disabled={saving}
                        >
                          <FaSave className="me-2" />
                          {saving ? 'Saving...' : 'Save Changes'}
                        </Button>
                        <Button
                          variant="outline-secondary"
                          onClick={handleCancel}
                          disabled={saving}
                        >
                          <FaTimes className="me-2" />
                          Cancel
                        </Button>
                      </div>
                    )}
                  </Form>
                </Card.Body>
              </Card>
            </Col>
          </Row>
        </Container>
      </div>
    </div>
  );
};

export default ProfilePage;
