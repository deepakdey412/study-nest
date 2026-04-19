import api from './api';

const superAdminService = {
  // Get all tutors
  getAllTutors: async () => {
    const response = await api.get('/superadmin/tutors');
    return response.data;
  },

  // Get pending tutors
  getPendingTutors: async () => {
    const response = await api.get('/superadmin/tutors/pending');
    return response.data;
  },

  // Approve or reject tutor
  approveTutor: async (tutorId, status) => {
    const response = await api.post('/superadmin/tutors/approve', {
      tutorId,
      status
    });
    return response.data;
  }
};

export default superAdminService;
