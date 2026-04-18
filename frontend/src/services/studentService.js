import api from './api';

const studentService = {
  // Get student profile
  getProfile: async () => {
    const response = await api.get('/students/profile');
    return response.data;
  },

  // Update student profile
  updateProfile: async (data) => {
    const response = await api.put('/students/profile', data);
    return response.data;
  }
};

export default studentService;
