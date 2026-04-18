import { useNavigate } from 'react-router-dom';
import { User } from 'lucide-react';

const Navbar = ({ user, onLogout }) => {
  const navigate = useNavigate();

  const styles = {
    navbar: {
      backgroundColor: '#fff',
      boxShadow: '0 2px 8px rgba(0, 0, 0, 0.08)',
      padding: '1rem 2rem',
      position: 'sticky',
      top: 0,
      zIndex: 100,
      marginLeft: '260px',
    },
    container: {
      maxWidth: '100%',
      margin: '0 auto',
      display: 'flex',
      justifyContent: 'flex-end',
      alignItems: 'center',
    },
    userInfo: {
      display: 'flex',
      alignItems: 'center',
      gap: '0.75rem',
      padding: '0.5rem 1rem',
      backgroundColor: '#F0F9FF',
      borderRadius: '9999px',
      border: '1px solid #BAE6FD',
    },
    userIcon: {
      width: '32px',
      height: '32px',
      borderRadius: '50%',
      backgroundColor: '#0EA5E9',
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      color: '#fff',
    },
    userName: {
      color: '#1E293B',
      fontWeight: 600,
      fontSize: '0.95rem',
    },
  };

  return (
    <nav style={styles.navbar}>
      <div style={styles.container}>
        {user && (
          <div style={styles.userInfo}>
            <div style={styles.userIcon}>
              <User size={18} />
            </div>
            <span style={styles.userName}>{user.name}</span>
          </div>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
