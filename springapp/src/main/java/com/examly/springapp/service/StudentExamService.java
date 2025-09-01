package com.examly.springapp.service;

import com.examly.springapp.model.*;
import com.examly.springapp.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentExamService {
    private final StudentExamRepository studentExamRepo;
    private final StudentAnswerRepository studentAnswerRepo;
    private final ExamRepository examRepo;
    private final QuestionRepository questionRepo;

    public StudentExamService(StudentExamRepository studentExamRepo, StudentAnswerRepository studentAnswerRepo,
                              ExamRepository examRepo, QuestionRepository questionRepo) {
        this.studentExamRepo = studentExamRepo;
        this.studentAnswerRepo = studentAnswerRepo;
        this.examRepo = examRepo;
        this.questionRepo = questionRepo;
    }

    public StudentExam startExam(Long examId, String studentUsername) {
        Exam exam = examRepo.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        if (!Boolean.TRUE.equals(exam.getIsActive())) {
            throw new RuntimeException("Exam is not active");
        }

        StudentExam studentExam = new StudentExam();
        studentExam.setExam(exam);
        studentExam.setStudentUsername(studentUsername);
        studentExam.setStartTime(LocalDateTime.now());
        studentExam.setStatus("IN_PROGRESS");

        return studentExamRepo.save(studentExam);
    }

    public StudentAnswer submitAnswer(Long studentExamId, Long questionId, String selectedOption) {
        StudentExam studentExam = studentExamRepo.findById(studentExamId)
                .orElseThrow(() -> new RuntimeException("StudentExam not found"));

        if ("COMPLETED".equals(studentExam.getStatus())) {
            throw new RuntimeException("Exam already completed");
        }

        Question question = questionRepo.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        StudentAnswer answer = new StudentAnswer();
        answer.setStudentExam(studentExam);
        answer.setQuestion(question);
        answer.setSelectedOption(selectedOption);
        answer.setIsCorrect(selectedOption.equalsIgnoreCase(question.getCorrectOption()));

        return studentAnswerRepo.save(answer);
    }

    public StudentExam completeExam(Long studentExamId) {
        StudentExam studentExam = studentExamRepo.findById(studentExamId)
                .orElseThrow(() -> new RuntimeException("StudentExam not found"));

        if ("COMPLETED".equals(studentExam.getStatus())) {
            throw new RuntimeException("Exam already completed");
        }

        int score = studentExam.getAnswers().stream()
                .filter(StudentAnswer::getIsCorrect)
                .mapToInt(a -> a.getQuestion().getMarks())
                .sum();

        studentExam.setEndTime(LocalDateTime.now());
        studentExam.setScore(score);
        studentExam.setStatus("COMPLETED");

        return studentExamRepo.save(studentExam);
    }

    public Object getAvailableExams() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAvailableExams'");
    }
}