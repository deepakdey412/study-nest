import { Nav } from 'react-bootstrap';
import { Link, useLocation } from 'react-router-dom';
import { FaHome, FaBook, FaClipboardList, FaCertificate, FaUser, FaChalkboardTeacher } from 'react-icons/fa';
import './Sidebar.css';

const Sidebar = ({ role }) => {
  const location = useLocation();

  const studentLinks = [
    { path: '/student/dashboard', icon: FaHome, label: 'Dashboard' },
    { path: '/student/modules', icon: FaBook, label: 'Modules' },
    { path: '/student/results', icon: FaClipboardList, label: 'Results' },
    { path: '/student/certificate', icon: FaCertificate, label: 'Certificate' },
    { path: '/student/profile', icon: FaUser, label: 'Profile' }
  ];

  const tutorLinks = [
    { path: '/tutor/dashboard', icon: FaHome, label: 'Dashboard' },
    { path: '/tutor/modules', icon: FaChalkboardTeacher, label: 'Modules' }
  ];

  const links = role === 'STUDENT' ? studentLinks : tutorLinks;

  return (
    <div className="sidebar bg-light border-end">
      <Nav className="flex-column p-3">
        {links.map((link) => {
          const Icon = link.icon;
          const isActive = location.pathname === link.path;
          return (
            <Nav.Link
              key={link.path}
              as={Link}
              to={link.path}
              className={`sidebar-link d-flex align-items-center mb-2 ${isActive ? 'active' : ''}`}
            >
              <Icon className="me-2" size={20} />
              <span>{link.label}</span>
            </Nav.Link>
          );
        })}
      </Nav>
    </div>
  );
};

export default Sidebar;
