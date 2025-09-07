package com.examly.springapp.service;

import com.examly.springapp.model.Question;
import com.examly.springapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question updateQuestion(Long id, Question details) {
        Question question = questionRepository.findById(id).orElse(null);
        if (question != null) {
            question.setExam(details.getExam());
            question.setQuestionText(details.getQuestionText());
            question.setOptionA(details.getOptionA());
            question.setOptionB(details.getOptionB());
            question.setOptionC(details.getOptionC());
            question.setOptionD(details.getOptionD());
            question.setCorrectOption(details.getCorrectOption());
            question.setMarks(details.getMarks());
            return questionRepository.save(question);
        }
        return null;
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
