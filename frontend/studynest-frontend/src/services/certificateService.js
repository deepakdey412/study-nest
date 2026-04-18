import api from './api';

const certificateService = {
  // Generate certificate
  generateCertificate: async () => {
    const response = await api.get('/certificate/generate');
    return response.data;
  }
};

export default certificateService;
