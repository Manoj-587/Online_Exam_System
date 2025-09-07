package com.examly.springapp.controller;

import com.examly.springapp.model.StudentAnswer;
import com.examly.springapp.service.StudentAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studentAnswers")
public class StudentAnswerController {

    @Autowired
    private StudentAnswerService studentAnswerService;

    @GetMapping
    public List<StudentAnswer> getAllStudentAnswers() {
        return studentAnswerService.getAllStudentAnswers();
    }

    @GetMapping("/{id}")
    public StudentAnswer getStudentAnswerById(@PathVariable Long id) {
        return studentAnswerService.getStudentAnswerById(id);
    }

    @PostMapping
    public StudentAnswer createStudentAnswer(@RequestBody StudentAnswer answer) {
        return studentAnswerService.createStudentAnswer(answer);
    }

    @PutMapping("/{id}")
    public StudentAnswer updateStudentAnswer(@PathVariable Long id, @RequestBody StudentAnswer details) {
        return studentAnswerService.updateStudentAnswer(id, details);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentAnswer(@PathVariable Long id) {
        studentAnswerService.deleteStudentAnswer(id);
    }
}
