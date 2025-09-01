package com.examly.springapp.service;

import com.examly.springapp.model.*;
import com.examly.springapp.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExamService {
    private final ExamRepository examRepo;
    private final QuestionRepository questionRepo;

    public ExamService(ExamRepository examRepo, QuestionRepository questionRepo) {
        this.examRepo = examRepo;
        this.questionRepo = questionRepo;
    }

    public Exam createExam(Exam exam) {
        return examRepo.save(exam);
    }

    public Question addQuestion(Long examId, Question question) {
        Exam exam = examRepo.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        question.setExam(exam);
        return questionRepo.save(question);
    }

    public List<Exam> getExamsByTeacher(String teacher) {
        return examRepo.findByCreatedBy(teacher);
    }

    public Exam updateExamStatus(Long examId, Boolean isActive) {
        Exam exam = examRepo.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        exam.setIsActive(isActive);
        return examRepo.save(exam);
    }

    public List<Exam> getActiveExams() {
        return examRepo.findByIsActiveTrue();
    }
}
