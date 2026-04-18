import api from './api';

const testService = {
  // Submit test
  submitTest: async (testData) => {
    const response = await api.post('/tests/submit', testData);
    return response.data;
  },

  // Get student results
  getResults: async () => {
    const response = await api.get('/tests/results');
    return response.data;
  }
};

export default testService;
