package com.examly.springapp.controller;

import com.examly.springapp.model.*;
import com.examly.springapp.service.ExamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class TeacherController {
    private final ExamService examService;

    public TeacherController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        return ResponseEntity.status(201).body(examService.createExam(exam));
    }

    @PostMapping("/{examId}/questions")
    public ResponseEntity<Question> addQuestion(@PathVariable Long examId, @RequestBody Question question) {
        return ResponseEntity.status(201).body(examService.addQuestion(examId, question));
    }

    @GetMapping
    public ResponseEntity<List<Exam>> getExamsByTeacher(@RequestParam String createdBy) {
        return ResponseEntity.ok(examService.getExamsByTeacher(createdBy));
    }

    @PatchMapping("/{examId}/status")
    public ResponseEntity<Exam> updateExamStatus(@PathVariable Long examId, @RequestBody Exam request) {
        return ResponseEntity.ok(examService.updateExamStatus(examId, request.getIsActive()));
    }
}
