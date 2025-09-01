package com.examly.springapp.service;

import com.examly.springapp.model.Exam;
import com.examly.springapp.model.Question;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ExamService {

    private final Map<Long, Exam> examStorage = new HashMap<>();
    private final Map<Long, List<Question>> examQuestions = new HashMap<>();
    private Long examIdCounter = 1L;
    private Long questionIdCounter = 1L;

    // ✅ Create a new exam with timestamp & defaults
    public Exam createExam(Exam exam, String teacherId) {
        exam.setExamId(examIdCounter++);
        exam.setCreatedBy(teacherId);
        exam.setCreatedAt(LocalDateTime.now());
        if (exam.getIsActive() == null) {
            exam.setActive(false); // default inactive
        }
        examStorage.put(exam.getExamId(), exam);
        return exam;
    }

    // ✅ Get all available (active) exams for students
    public List<Exam> getAvailableExams() {
        List<Exam> result = new ArrayList<>();
        for (Exam exam : examStorage.values()) {
            if (Boolean.TRUE.equals(exam.getIsActive())) {
                result.add(exam);
            }
        }
        return result;
    }

    // ✅ Get exams created by a teacher
    public List<Exam> getExamsByTeacher(String teacherId) {
        List<Exam> result = new ArrayList<>();
        for (Exam exam : examStorage.values()) {
            if (exam.getCreatedBy() != null && exam.getCreatedBy().equals(teacherId)) {
                result.add(exam);
            }
        }
        return result;
    }

    // ✅ Add question to exam (throws if exam not found)
    public Question addQuestion(Long examId, Question question) {
        Exam exam = examStorage.get(examId);
        if (exam == null) {
            throw new NoSuchElementException("Exam not found");
        }
        question.setQuestionId(questionIdCounter++);
        question.setExam(exam); // sets exam properly
        examQuestions.computeIfAbsent(examId, k -> new ArrayList<>()).add(question);
        return question;
    }
        // ✅ Get questions by exam
    public List<Question> getQuestions(Long examId) {
        return examQuestions.getOrDefault(examId, new ArrayList<>());
    }

    // ✅ Set exam active/inactive
    public boolean setExamActiveStatus(Long examId, boolean status) {
        Exam exam = examStorage.get(examId);
        if (exam != null) {
            exam.setActive(status);
            return true;
        }
        return false;
    }

    // ✅ Activate exam directly
    public boolean activateExam(Long examId) {
        return setExamActiveStatus(examId, true);
    }

    // ✅ For student service usage
    public Exam getExam(Long examId) {
        return examStorage.get(examId);
    }

    public List<Exam> getAllExams() {
        return new ArrayList<>(examStorage.values());
    }
}