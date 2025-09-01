package com.examly.springapp.controller;

import com.examly.springapp.model.StudentAnswer;
import com.examly.springapp.service.StudentExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student/exams")
public class StudentController {

    private final StudentExamService studentExamService;

    public StudentController(StudentExamService studentExamService) {
        this.studentExamService = studentExamService;
    }

    @GetMapping
    public ResponseEntity<List<?>> getAvailableExams() {
        return ResponseEntity.ok(studentExamService.getAvailableExams());
    }

    @PostMapping("/{examId}/start")
    public ResponseEntity<Map<String, Object>> startExam(
            @PathVariable Long examId,
            @RequestBody Map<String, String> body) {
        String studentUsername = body.get("studentUsername");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentExamService.startExam(examId, studentUsername));
    }

    @PostMapping("/{studentExamId}/answers")
    public ResponseEntity<StudentAnswer> submitAnswer(
            @PathVariable Long studentExamId,
            @RequestBody Map<String, Object> body) {
        Long questionId = Long.valueOf(body.get("questionId").toString());
        String selectedOption = body.get("selectedOption").toString();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentExamService.submitAnswer(studentExamId, questionId, selectedOption));
    }

    @PostMapping("/{studentExamId}/complete")
    public ResponseEntity<Map<String, Object>> completeExam(
            @PathVariable Long studentExamId) {
        return ResponseEntity.ok(studentExamService.completeExam(studentExamId));
    }

    @GetMapping("/{studentExamId}/results")
    public ResponseEntity<Map<String, Object>> getResults(
            @PathVariable Long studentExamId) {
        return ResponseEntity.ok(studentExamService.getResults(studentExamId));
    }
}
