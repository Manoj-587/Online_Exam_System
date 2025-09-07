package com.examly.springapp.controller;

import com.examly.springapp.model.Exam;
import com.examly.springapp.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    // Get all exams
    @GetMapping
    public List<Exam> getAllExams() {
        return examService.getAllExams();
    }

    // Get exam by ID
    @GetMapping("/{id}")
    public Exam getExamById(@PathVariable Long id) {
        return examService.getExamById(id);
    }

    // Create new exam
    @PostMapping
    public Exam createExam(@RequestBody Exam exam) {
        return examService.createExam(exam);
    }

    // Update exam
    @PutMapping("/{id}")
    public Exam updateExam(@PathVariable Long id, @RequestBody Exam examDetails) {
        return examService.updateExam(id, examDetails);
    }

    // Delete exam
    @DeleteMapping("/{id}")
    public void deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
    }
}
