package com.examly.springapp.service;

import com.examly.springapp.model.StudentAnswer;
import com.examly.springapp.repository.StudentAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentAnswerService {

    @Autowired
    private StudentAnswerRepository studentAnswerRepository;

    public List<StudentAnswer> getAllStudentAnswers() {
        return studentAnswerRepository.findAll();
    }

    public StudentAnswer getStudentAnswerById(Long id) {
        return studentAnswerRepository.findById(id).orElse(null);
    }

    public StudentAnswer createStudentAnswer(StudentAnswer answer) {
        return studentAnswerRepository.save(answer);
    }

    public StudentAnswer updateStudentAnswer(Long id, StudentAnswer details) {
        StudentAnswer answer = studentAnswerRepository.findById(id).orElse(null);
        if (answer != null) {
            answer.setStudentExam(details.getStudentExam());
            answer.setQuestion(details.getQuestion());
            answer.setSelectedOption(details.getSelectedOption());
            answer.setIsCorrect(details.getIsCorrect());
            return studentAnswerRepository.save(answer);
        }
        return null;
    }

    public void deleteStudentAnswer(Long id) {
        studentAnswerRepository.deleteById(id);
    }
}
