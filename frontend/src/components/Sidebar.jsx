import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Home, BookOpen, ClipboardList, Award, User, GraduationCap, LogOut, Settings, Menu, X } from 'lucide-react';
import { useState, useEffect } from 'react';

const Sidebar = ({ role, onLogout, user }) => {
  const location = useLocation();
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(false);
  const [isMobile, setIsMobile] = useState(false);

  useEffect(() => {
    const checkMobile = () => {
      setIsMobile(window.innerWidth < 1024);
      if (window.innerWidth >= 1024) {
        setIsOpen(false);
      }
    };
    
    checkMobile();
    window.addEventListener('resize', checkMobile);
    return () => window.removeEventListener('resize', checkMobile);
  }, []);

  const handleLogout = () => {
    onLogout();
    navigate('/');
  };

  const toggleSidebar = () => {
    setIsOpen(!isOpen);
  };

  const closeSidebar = () => {
    if (isMobile) {
      setIsOpen(false);
    }
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
    { path: '/tutor/modules', icon: GraduationCap, label: 'Modules', requiresApproval: true },
    { path: '/tutor/manage-modules', icon: Settings, label: 'Manage Modules', requiresApproval: true }
  ];

  const superAdminLinks = [
    { path: '/superadmin/dashboard', icon: Home, label: 'Dashboard' }
  ];

  let links = role === 'STUDENT' ? studentLinks : role === 'SUPER_ADMIN' ? superAdminLinks : tutorLinks;
  
  // Filter out links that require approval if tutor is pending
  if (role === 'TUTOR' && user?.approvalStatus === 'PENDING') {
    links = links.filter(link => !link.requiresApproval);
  }

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
    hamburger: {
      position: 'fixed',
      top: '1rem',
      left: '1rem',
      zIndex: 1100,
      backgroundColor: '#1E293B',
      color: '#fff',
      border: 'none',
      borderRadius: '8px',
      padding: '0.75rem',
      cursor: 'pointer',
      display: isMobile ? 'flex' : 'none',
      alignItems: 'center',
      justifyContent: 'center',
      boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
    },
    overlay: {
      position: 'fixed',
      top: 0,
      left: 0,
      right: 0,
      bottom: 0,
      backgroundColor: 'rgba(0, 0, 0, 0.5)',
      zIndex: 1000,
      display: isMobile && isOpen ? 'block' : 'none',
    },
    sidebar: {
      width: '260px',
      backgroundColor: '#1E293B',
      borderRight: '1px solid #334155',
      padding: '0',
      minHeight: '100vh',
      position: 'fixed',
      top: '0',
      left: isMobile ? (isOpen ? '0' : '-260px') : '0',
      bottom: '0',
      overflowY: 'auto',
      zIndex: 1050,
      display: 'flex',
      flexDirection: 'column',
      transition: 'left 0.3s ease',
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
    <>
      {/* Hamburger Menu Button */}
      <button style={styles.hamburger} onClick={toggleSidebar}>
        {isOpen ? <X size={24} /> : <Menu size={24} />}
      </button>

      {/* Overlay */}
      <div style={styles.overlay} onClick={closeSidebar} />

      {/* Sidebar */}
      <div style={styles.sidebar}>
      <div style={styles.logoSection} onClick={() => navigate('/')}>
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
              onClick={closeSidebar}
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
    </>
  );
};

export default Sidebar;
