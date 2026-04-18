import api from './api';

const questionService = {
  // Get questions by module (for students - without answers)
  getQuestionsByModule: async (moduleId) => {
    const response = await api.get(`/questions/module/${moduleId}`);
    return response.data;
  },

  // Get questions for tutor (with answers)
  getQuestionsWithAnswers: async (moduleId) => {
    const response = await api.get(`/questions/module/${moduleId}/with-answers`);
    return response.data;
  }
};

export default questionService;
