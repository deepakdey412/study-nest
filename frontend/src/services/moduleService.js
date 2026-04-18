import api from './api';

const moduleService = {
  // Get all modules
  getAllModules: async () => {
    const response = await api.get('/modules');
    return response.data;
  },

  // Get modules by semester
  getModulesBySemester: async (semester) => {
    const response = await api.get(`/modules/semester/${semester}`);
    return response.data;
  },

  // Get module by ID
  getModuleById: async (id) => {
    const response = await api.get(`/modules/${id}`);
    return response.data;
  }
};

export default moduleService;
