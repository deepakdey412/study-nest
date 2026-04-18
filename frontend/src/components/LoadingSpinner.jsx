
import { Spinner } from 'react-bootstrap';
const LoadingSpinner = ({ message = 'Loading...' }) => {
  return (
    <div className="d-flex flex-column justify-content-center align-items-center" style={{ minHeight: '400px' }}>
      <Spinner animation="border" variant="primary" style={{ width: '3rem', height: '3rem' }} />
      <p className="mt-3 text-muted">{message}</p>
    </div>
  );
};

export default LoadingSpinner;
