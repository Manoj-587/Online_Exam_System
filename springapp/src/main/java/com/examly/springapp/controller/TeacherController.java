package com.examly.springapp.controller;

import com.examly.springapp.model.Exam;
import com.examly.springapp.model.Question;
import com.examly.springapp.service.ExamService;
import org.springframework.http.ResponseEntity;
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
        Exam saved = examService.createExam(exam);
        return ResponseEntity.status(201).body(saved);
    }

    @PostMapping("/{examId}/questions")
    public ResponseEntity<Question> addQuestion(@PathVariable Long examId, @RequestBody Question question) {
        Question saved = examService.addQuestion(examId, question);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Exam>> getExamsByTeacher(@RequestParam(name = "createdBy") String createdBy) {
        return ResponseEntity.ok(examService.getExamsByTeacher(createdBy));
    }

    @PatchMapping("/{examId}/status")
    public ResponseEntity<Exam> setExamStatus(@PathVariable Long examId, @RequestBody Map<String, Object> body) {
        Boolean isActive = (Boolean) body.get("isActive");
        Exam updated = examService.setExamActiveStatus(examId, isActive);
        return ResponseEntity.ok(updated);
    }
}
