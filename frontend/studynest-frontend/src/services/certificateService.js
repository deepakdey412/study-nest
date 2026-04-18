import api from './api';

const certificateService = {
  // Check certificate eligibility
  checkEligibility: async () => {
    const response = await api.get('/certificate/eligibility');
    return response.data;
  },

  // Generate certificate
  generateCertificate: async () => {
    const response = await api.get('/certificate/generate');
    return response.data;
  }
};

export default certificateService;
