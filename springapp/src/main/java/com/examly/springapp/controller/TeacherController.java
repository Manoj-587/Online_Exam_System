package com.examly.springapp.controller;

import com.examly.springapp.model.Exam;
import com.examly.springapp.model.Question;
import com.examly.springapp.service.ExamService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/exams")
public class TeacherController {

    private final ExamService examService;

    public TeacherController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        return ResponseEntity.status(HttpStatus.CREATED).body(examService.createExam(exam));
    }

    @PostMapping("/{examId}/questions")
    public ResponseEntity<Question> addQuestion(@PathVariable Long examId, @RequestBody Question question) {
        return ResponseEntity.status(HttpStatus.CREATED).body(examService.addQuestion(examId, question));
    }

    @GetMapping
    public ResponseEntity<List<Exam>> getExamsByTeacher(@RequestParam String createdBy) {
        return ResponseEntity.ok(examService.getExamsByTeacher(createdBy));
    }

    @PatchMapping("/{examId}/status")
    public ResponseEntity<Exam> updateExamStatus(@PathVariable Long examId, @RequestBody Map<String, Boolean> req) {
        boolean isActive = req.get("isActive");
        return ResponseEntity.ok(examService.setExamActiveStatus(examId, isActive));
    }
}
