import api from './api';

const authService = {
  // Student Registration
  registerStudent: async (data) => {
    const response = await api.post('/auth/student/register', data);
    if (response.data.token) {
      localStorage.setItem('token', response.data.token);
      localStorage.setItem('user', JSON.stringify(response.data));
    }
    return response.data;
  },

  // Tutor Registration
  registerTutor: async (data) => {
    const response = await api.post('/auth/tutor/register', data);
    // Don't save token for pending tutors (token will be null)
    // They need admin approval first
    return response.data;
  },

  // Login
  login: async (credentials) => {
    const response = await api.post('/auth/login', credentials);
    if (response.data.token) {
      localStorage.setItem('token', response.data.token);
      localStorage.setItem('user', JSON.stringify(response.data));
    }
    return response.data;
  },

  // Logout
  logout: () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
  },

  // Get Current User
  getCurrentUser: () => {
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user) : null;
  },

  // Check if authenticated
  isAuthenticated: () => {
    return !!localStorage.getItem('token');
  }
};

export default authService;
