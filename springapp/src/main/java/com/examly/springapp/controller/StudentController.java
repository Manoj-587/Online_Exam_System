package com.examly.springapp.controller;
import com.examly.springapp.model.*;
import com.examly.springapp.service.ExamService;
import com.examly.springapp.service.StudentExamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student/exams")
public class StudentController {
    private final ExamService examService;
    private final StudentExamService studentExamService;

    public StudentController(ExamService examService, StudentExamService studentExamService) {
        this.examService = examService;
        this.studentExamService = studentExamService;
    }

    @GetMapping
    public ResponseEntity<List<Exam>> getAvailableExams() {
        return ResponseEntity.ok(examService.getActiveExams());
    }

    @PostMapping("/{examId}/start")
    public ResponseEntity<StudentExam> startExam(@PathVariable Long examId, @RequestBody Map<String, String> body) {
        return ResponseEntity.status(201).body(studentExamService.startExam(examId, body.get("studentUsername")));
    }

    @PostMapping("/{studentExamId}/answers")
    public ResponseEntity<StudentAnswer> submitAnswer(@PathVariable Long studentExamId, @RequestBody Map<String, Object> body) {
        return ResponseEntity.status(201).body(
                studentExamService.submitAnswer(
                        studentExamId,
                        Long.valueOf(body.get("questionId").toString()),
                        body.get("selectedOption").toString()
                )
        );
    }

    @PostMapping("/{studentExamId}/complete")
    public ResponseEntity<StudentExam> completeExam(@PathVariable Long studentExamId) {
        return ResponseEntity.ok(studentExamService.completeExam(studentExamId));
    }
}
