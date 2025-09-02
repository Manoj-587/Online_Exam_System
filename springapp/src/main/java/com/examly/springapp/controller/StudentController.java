package com.examly.springapp.controller;

import com.examly.springapp.model.Exam;
import com.examly.springapp.model.StudentAnswer;
import com.examly.springapp.service.StudentExamService;
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
    public ResponseEntity<List<Exam>> getAvailableExams() {
        return ResponseEntity.ok(studentExamService.getAvailableExams());
    }

    @PostMapping("/{examId}/start")
    public ResponseEntity<Map<String, Object>> startExam(
            @PathVariable Long examId,
            @RequestBody Map<String, String> body
    ) {
        String studentUsername = body.get("studentUsername");
        Map<String, Object> resp = studentExamService.startExam(examId, studentUsername);
        return ResponseEntity.status(201).body(resp);
    }

    @PostMapping("/{studentExamId}/answers")
    public ResponseEntity<StudentAnswer> submitAnswer(
            @PathVariable Long studentExamId,
            @RequestBody Map<String, Object> body
    ) {
        Long questionId = Long.valueOf(body.get("questionId").toString());
        String selectedOption = body.get("selectedOption").toString();
        StudentAnswer ans = studentExamService.submitAnswer(studentExamId, questionId, selectedOption);
        return ResponseEntity.status(201).body(ans);
    }

    @PostMapping("/{studentExamId}/complete")
    public ResponseEntity<Map<String, Object>> completeExam(@PathVariable Long studentExamId) {
        Map<String, Object> resp = studentExamService.completeExam(studentExamId);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{studentExamId}/results")
    public ResponseEntity<Map<String, Object>> getResults(@PathVariable Long studentExamId) {
        Map<String, Object> resp = studentExamService.getResults(studentExamId);
        return ResponseEntity.ok(resp);
    }
}
