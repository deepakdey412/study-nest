import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Home, BookOpen, ClipboardList, Award, User, GraduationCap, LogOut, Settings } from 'lucide-react';

const Sidebar = ({ role, onLogout, user }) => {
  const location = useLocation();
  const navigate = useNavigate();

  const handleLogout = () => {
    onLogout();
    navigate('/');
  };

  const studentLinks = [
    { path: '/student/dashboard', icon: Home, label: 'Dashboard' },
    { path: '/student/modules', icon: BookOpen, label: 'Modules' },
    { path: '/student/results', icon: ClipboardList, label: 'Results' },
    { path: '/student/certificate', icon: Award, label: 'Certificate' },
    { path: '/student/profile', icon: User, label: 'Profile' }
  ];

  const tutorLinks = [
    { path: '/tutor/dashboard', icon: Home, label: 'Dashboard' },
    { path: '/tutor/modules', icon: GraduationCap, label: 'Modules' },
    { path: '/tutor/manage-modules', icon: Settings, label: 'Manage Modules' }
  ];

  const links = role === 'STUDENT' ? studentLinks : tutorLinks;

  // Logo SVG Component
  const Logo = ({ color = '#0EA5E9' }) => (
    <svg width="32" height="32" viewBox="0 0 32 32" fill="none">
      <path d="M16 4L8 10V18C8 22.4183 11.5817 26 16 26C20.4183 26 24 22.4183 24 18V10L16 4Z" fill={color} opacity="0.2"/>
      <path d="M16 4L8 10V18C8 22.4183 11.5817 26 16 26C20.4183 26 24 22.4183 24 18V10L16 4Z" stroke={color} strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
      <path d="M16 12L12 14.5V19C12 20.6569 13.3431 22 15 22H17C18.6569 22 20 20.6569 20 19V14.5L16 12Z" fill={color}/>
      <circle cx="16" cy="8" r="2" fill="#7DD3FC"/>
    </svg>
  );

  const styles = {
    sidebar: {
      width: '260px',
      backgroundColor: '#1E293B',
      borderRight: '1px solid #334155',
      padding: '0',
      minHeight: '100vh',
      position: 'fixed',
      top: '0',
      left: '0',
      bottom: '0',
      overflowY: 'auto',
      zIndex: 101,
      display: 'flex',
      flexDirection: 'column',
    },
    logoSection: {
      display: 'flex',
      alignItems: 'center',
      gap: '0.75rem',
      padding: '1.5rem 1rem',
      borderBottom: '1px solid #334155',
      cursor: 'pointer',
      backgroundColor: '#1E293B',
    },
    logoText: {
      color: '#fff',
      fontWeight: 700,
      fontSize: '1.25rem',
    },
    nav: {
      display: 'flex',
      flexDirection: 'column',
      gap: '0.5rem',
      padding: '1.5rem 1rem',
      flex: 1,
    },
    link: {
      display: 'flex',
      alignItems: 'center',
      gap: '0.75rem',
      padding: '0.875rem 1rem',
      borderRadius: '8px',
      textDecoration: 'none',
      color: '#94A3B8',
      fontWeight: 500,
      transition: 'all 0.25s ease',
      cursor: 'pointer',
    },
    linkActive: {
      backgroundColor: '#0EA5E9',
      color: '#fff',
      fontWeight: 600,
    },
    logoutSection: {
      padding: '1rem',
      borderTop: '1px solid #334155',
      marginTop: 'auto',
    },
    logoutButton: {
      display: 'flex',
      alignItems: 'center',
      gap: '0.75rem',
      padding: '0.875rem 1rem',
      borderRadius: '8px',
      backgroundColor: 'transparent',
      color: '#EF4444',
      fontWeight: 500,
      cursor: 'pointer',
      transition: 'all 0.25s ease',
      border: 'none',
      width: '100%',
      fontSize: '1rem',
    },
  };

  return (
    <div style={styles.sidebar}>
      <div style={styles.logoSection} onClick={() => navigate(role === 'STUDENT' ? '/student/dashboard' : '/tutor/dashboard')}>
        <Logo color="#0EA5E9" />
        <span style={styles.logoText}>StudyNest</span>
      </div>
      
      <nav style={styles.nav}>
        {links.map((link) => {
          const Icon = link.icon;
          const isActive = location.pathname === link.path;
          return (
            <Link
              key={link.path}
              to={link.path}
              style={{
                ...styles.link,
                ...(isActive ? styles.linkActive : {}),
              }}
              onMouseEnter={(e) => {
                if (!isActive) {
                  e.currentTarget.style.backgroundColor = '#334155';
                  e.currentTarget.style.color = '#fff';
                }
              }}
              onMouseLeave={(e) => {
                if (!isActive) {
                  e.currentTarget.style.backgroundColor = 'transparent';
                  e.currentTarget.style.color = '#94A3B8';
                }
              }}
            >
              <Icon size={20} />
              <span>{link.label}</span>
            </Link>
          );
        })}
      </nav>

      <div style={styles.logoutSection}>
        <button
          style={styles.logoutButton}
          onClick={handleLogout}
          onMouseEnter={(e) => {
            e.currentTarget.style.backgroundColor = '#7F1D1D';
            e.currentTarget.style.color = '#fff';
          }}
          onMouseLeave={(e) => {
            e.currentTarget.style.backgroundColor = 'transparent';
            e.currentTarget.style.color = '#EF4444';
          }}
        >
          <LogOut size={20} />
          <span>Logout</span>
        </button>
      </div>
    </div>
  );
};

export default Sidebar;
