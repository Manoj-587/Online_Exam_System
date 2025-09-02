package com.examly.springapp.service;

import com.examly.springapp.model.Exam;
import com.examly.springapp.model.Question;
import com.examly.springapp.repository.ExamRepository;
import com.examly.springapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public Exam createExam(Exam exam) {
        if (exam.getCreatedAt() == null) {
            exam.setCreatedAt(LocalDateTime.now());
        }
        if (exam.getIsActive() == null) {
            exam.setIsActive(false);
        }
        return examRepository.save(exam);
    }

    public Question addQuestion(Long examId, Question question) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new IllegalArgumentException("Exam not found"));
        question.setExam(exam);
        return questionRepository.save(question);
    }

    public List<Exam> getExamsByTeacher(String teacherUsername) {
        return examRepository.findByCreatedBy(teacherUsername);
    }

    public Exam setExamActiveStatus(Long examId, Boolean isActive) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new IllegalArgumentException("Exam not found"));
        exam.setIsActive(isActive);
        return examRepository.save(exam);
    }
}
