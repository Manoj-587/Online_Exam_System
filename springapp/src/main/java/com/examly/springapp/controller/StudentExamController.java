package com.examly.springapp.controller;

import com.examly.springapp.model.StudentExam;
import com.examly.springapp.service.StudentExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studentExams")
public class StudentExamController {

    @Autowired
    private StudentExamService studentExamService;

    @GetMapping
    public List<StudentExam> getAllStudentExams() {
        return studentExamService.getAllStudentExams();
    }

    @GetMapping("/{id}")
    public StudentExam getStudentExamById(@PathVariable Long id) {
        return studentExamService.getStudentExamById(id);
    }

    @PostMapping
    public StudentExam createStudentExam(@RequestBody StudentExam studentExam) {
        return studentExamService.createStudentExam(studentExam);
    }

    @PutMapping("/{id}")
    public StudentExam updateStudentExam(@PathVariable Long id, @RequestBody StudentExam details) {
        return studentExamService.updateStudentExam(id, details);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentExam(@PathVariable Long id) {
        studentExamService.deleteStudentExam(id);
    }
}
