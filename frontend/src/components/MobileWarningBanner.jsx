import { useState, useEffect } from 'react';
import { Alert } from 'react-bootstrap';
import { FaLaptop, FaTimes } from 'react-icons/fa';

const MobileWarningBanner = () => {
  const [show, setShow] = useState(false);
  const [dismissed, setDismissed] = useState(false);

  useEffect(() => {
    // Check if user dismissed the banner in this session
    const isDismissed = sessionStorage.getItem('mobileWarningDismissed');
    if (isDismissed) {
      setDismissed(true);
      return;
    }

    // Show banner only on mobile/tablet (screen width < 1024px)
    const checkScreenSize = () => {
      setShow(window.innerWidth < 1024);
    };

    checkScreenSize();
    window.addEventListener('resize', checkScreenSize);

    return () => window.removeEventListener('resize', checkScreenSize);
  }, []);

  const handleDismiss = () => {
    setDismissed(true);
    sessionStorage.setItem('mobileWarningDismissed', 'true');
  };

  if (!show || dismissed) return null;

  return (
    <Alert 
      variant="warning" 
      className="mobile-warning-banner m-0 rounded-0 d-flex align-items-center justify-content-between"
      style={{
        position: 'sticky',
        top: 0,
        zIndex: 1050,
        borderRadius: 0,
        fontSize: '0.9rem',
        padding: '0.75rem 1rem'
      }}
    >
      <div className="d-flex align-items-center gap-2">
        <FaLaptop size={20} />
        <span>
          <strong>Better Experience:</strong> Use a laptop or desktop for optimal viewing
        </span>
      </div>
      <button
        onClick={handleDismiss}
        className="btn btn-sm btn-link text-dark p-0"
        style={{ textDecoration: 'none' }}
        aria-label="Dismiss"
      >
        <FaTimes size={18} />
      </button>
    </Alert>
  );
};

export default MobileWarningBanner;
