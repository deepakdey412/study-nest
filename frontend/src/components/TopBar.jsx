import { User } from 'lucide-react';

const TopBar = ({ user }) => {
  const styles = {
    topBar: {
      position: 'fixed',
      top: 0,
      right: 0,
      left: window.innerWidth >= 1024 ? '260px' : '0',
      height: '60px',
      backgroundColor: '#fff',
      borderBottom: '1px solid #E5E7EB',
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'flex-end',
      padding: window.innerWidth < 1024 ? '0 1rem 0 4rem' : '0 2rem',
      zIndex: 100,
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
      display: window.innerWidth < 640 ? 'none' : 'block',
    },
  };

  return (
    <div style={styles.topBar}>
      {user && (
        <div style={styles.userInfo}>
          <div style={styles.userIcon}>
            <User size={18} />
          </div>
          <span style={styles.userName}>{user.name}</span>
        </div>
      )}
    </div>
  );
};

export default TopBar;
