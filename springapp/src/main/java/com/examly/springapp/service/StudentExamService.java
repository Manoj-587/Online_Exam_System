package com.examly.springapp.service;

import com.examly.springapp.model.*;
import com.examly.springapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class StudentExamService {

    @Autowired
    private StudentExamRepository studentExamRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private StudentAnswerRepository studentAnswerRepository;

    public List<Exam> getAvailableExams() {
        return examRepository.findByIsActiveTrue();
    }

    public Map<String, Object> startExam(Long examId, String studentUsername) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new IllegalArgumentException("Exam not found"));
        if (exam.getIsActive() == null || !exam.getIsActive()) {
            throw new IllegalArgumentException("Exam not active");
        }

        List<StudentExam> existing = studentExamRepository.findByExamAndStudentUsernameAndStatusIn(
                exam, studentUsername, List.of("IN_PROGRESS", "NOT_STARTED")
        );
        if (existing != null && !existing.isEmpty()) {
            throw new IllegalArgumentException("You already have an active attempt");
        }

        StudentExam studentExam = new StudentExam();
        studentExam.setExam(exam);
        studentExam.setStudentUsername(studentUsername);
        studentExam.setStatus("IN_PROGRESS");
        studentExam.setStartTime(LocalDateTime.now());
        studentExam = studentExamRepository.save(studentExam);

        List<Question> questions = questionRepository.findByExam(exam);
        List<Map<String, Object>> qlist = new ArrayList<>();
        for (Question q : questions) {
            Map<String, Object> qm = new HashMap<>();
            qm.put("questionId", q.getQuestionId());
            qm.put("questionText", q.getQuestionText());
            qm.put("optionA", q.getOptionA());
            qm.put("optionB", q.getOptionB());
            qm.put("optionC", q.getOptionC());
            qm.put("optionD", q.getOptionD());
            qm.put("marks", q.getMarks());
            qlist.add(qm);
        }

        Map<String, Object> resp = new HashMap<>();
        resp.put("studentExamId", studentExam.getStudentExamId());
        resp.put("questions", qlist);
        return resp;
    }

    public StudentAnswer submitAnswer(Long studentExamId, Long questionId, String selectedOption) {
        StudentExam studentExam = studentExamRepository.findById(studentExamId)
                .orElseThrow(() -> new IllegalArgumentException("StudentExam not found"));
        if ("COMPLETED".equalsIgnoreCase(studentExam.getStatus())) {
            throw new IllegalArgumentException("Exam already completed");
        }

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));

        StudentAnswer existing = studentAnswerRepository.findByStudentExamAndQuestion(studentExam, question);
        if (existing != null) {
            return existing;
        }

        StudentAnswer answer = new StudentAnswer();
        answer.setStudentExam(studentExam);
        answer.setQuestion(question);
        answer.setSelectedOption(selectedOption);
        boolean correct = question.getCorrectOption() != null &&
                question.getCorrectOption().equalsIgnoreCase(selectedOption);
        answer.setIsCorrect(correct);

        answer = studentAnswerRepository.save(answer);
        return answer;
    }

    public Map<String, Object> completeExam(Long studentExamId) {
        StudentExam studentExam = studentExamRepository.findById(studentExamId)
                .orElseThrow(() -> new IllegalArgumentException("StudentExam not found"));
        if ("COMPLETED".equalsIgnoreCase(studentExam.getStatus())) {
            throw new IllegalArgumentException("Exam already completed");
        }

        List<StudentAnswer> answers = studentAnswerRepository.findByStudentExam(studentExam);
        int total = 0;
        for (StudentAnswer a : answers) {
            if (Boolean.TRUE.equals(a.getIsCorrect()) && a.getQuestion() != null && a.getQuestion().getMarks() != null) {
                total += a.getQuestion().getMarks();
            }
        }

        studentExam.setEndTime(LocalDateTime.now());
        studentExam.setStatus("COMPLETED");
        studentExam.setScore(total);
        studentExamRepository.save(studentExam);

        Map<String, Object> resp = new HashMap<>();
        resp.put("studentExamId", studentExamId);
        resp.put("finalScore", total);
        return resp;
    }

    public Map<String, Object> getResults(Long studentExamId) {
        StudentExam studentExam = studentExamRepository.findById(studentExamId)
                .orElseThrow(() -> new IllegalArgumentException("StudentExam not found"));
        if (!"COMPLETED".equalsIgnoreCase(studentExam.getStatus())) {
            throw new IllegalArgumentException("Exam not yet completed");
        }

        Exam exam = studentExam.getExam();
        List<StudentAnswer> answers = studentAnswerRepository.findByStudentExam(studentExam);
        int score = studentExam.getScore() == null ? 0 : studentExam.getScore();

        List<Map<String, Object>> qlist = new ArrayList<>();
        for (StudentAnswer ans : answers) {
            Question q = ans.getQuestion();
            Map<String, Object> qm = new HashMap<>();
            qm.put("questionId", q.getQuestionId());
            qm.put("questionText", q.getQuestionText());
            qm.put("optionA", q.getOptionA());
            qm.put("optionB", q.getOptionB());
            qm.put("optionC", q.getOptionC());
            qm.put("optionD", q.getOptionD());
            qm.put("correctOption", q.getCorrectOption());
            qm.put("marks", q.getMarks());
            qm.put("selectedOption", ans.getSelectedOption());
            qm.put("isCorrect", ans.getIsCorrect());
            qm.put("marksEarned", (Boolean.TRUE.equals(ans.getIsCorrect()) ? q.getMarks() : 0));
            qlist.add(qm);
        }

        Map<String, Object> resp = new HashMap<>();
        resp.put("examTitle", exam.getTitle());
        resp.put("description", exam.getDescription());
        resp.put("score", score);
        resp.put("questions", qlist);
        return resp;
    }
}
