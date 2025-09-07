package com.examly.springapp.service;

import com.examly.springapp.model.Exam;
import com.examly.springapp.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    // Get all exams
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    // Get exam by ID
    public Exam getExamById(Long id) {
        return examRepository.findById(id).orElse(null);
    }

    // Create new exam
    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    // Update exam
    public Exam updateExam(Long id, Exam examDetails) {
        Exam exam = examRepository.findById(id).orElse(null);
        if (exam != null) {
            exam.setTitle(examDetails.getTitle());
            exam.setDescription(examDetails.getDescription());
            exam.setDuration(examDetails.getDuration());
            exam.setCreatedBy(examDetails.getCreatedBy());
            exam.setCreatedAt(examDetails.getCreatedAt());
            exam.setIsActive(examDetails.getIsActive());
            return examRepository.save(exam);
        }
        return null;
    }

    // Delete exam
    public void deleteExam(Long id) {
        examRepository.deleteById(id);
    }
}
