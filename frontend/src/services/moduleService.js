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
  },

  // Create new module (Tutor only)
  createModule: async (moduleData) => {
    const response = await api.post('/modules', moduleData);
    return response.data;
  },

  // Update module (Tutor only)
  updateModule: async (id, moduleData) => {
    const response = await api.put(`/modules/${id}`, moduleData);
    return response.data;
  },

  // Delete module (Tutor only)
  deleteModule: async (id) => {
    const response = await api.delete(`/modules/${id}`);
    return response.data;
  }
};

export default moduleService;
