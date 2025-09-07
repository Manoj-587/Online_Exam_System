package com.examly.springapp.service;

import com.examly.springapp.model.StudentExam;
import com.examly.springapp.repository.StudentExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentExamService {

    @Autowired
    private StudentExamRepository studentExamRepository;

    public List<StudentExam> getAllStudentExams() {
        return studentExamRepository.findAll();
    }

    public StudentExam getStudentExamById(Long id) {
        return studentExamRepository.findById(id).orElse(null);
    }

    public StudentExam createStudentExam(StudentExam studentExam) {
        return studentExamRepository.save(studentExam);
    }

    public StudentExam updateStudentExam(Long id, StudentExam details) {
        StudentExam studentExam = studentExamRepository.findById(id).orElse(null);
        if (studentExam != null) {
            studentExam.setExam(details.getExam());
            studentExam.setStudentUsername(details.getStudentUsername());
            studentExam.setStartTime(details.getStartTime());
            studentExam.setEndTime(details.getEndTime());
            studentExam.setScore(details.getScore());
            studentExam.setStatus(details.getStatus());
            return studentExamRepository.save(studentExam);
        }
        return null;
    }

    public void deleteStudentExam(Long id) {
        studentExamRepository.deleteById(id);
    }
}
