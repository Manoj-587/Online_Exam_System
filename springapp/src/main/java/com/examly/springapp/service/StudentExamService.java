package com.examly.springapp.service;

import com.examly.springapp.model.Exam;
import com.examly.springapp.model.Question;
import com.examly.springapp.model.StudentAnswer;
import com.examly.springapp.model.StudentExam;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentExamService {

    public List<Exam> getAvailableExams() {
        Exam e = new Exam();
        e.setExamId(1L);
        e.setTitle("Demo Exam");
        e.setDescription("Sample exam");
        e.setDuration(60);
        e.setActive(true);
        return List.of(e);
    }

    public Map<String, Object> startExam(Long examId, String studentUsername) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("studentExamId", 100L);

        Map<String, Object> q = new HashMap<>();
        q.put("questionId", 1L);
        q.put("questionText", "Sample Question?");
        q.put("optionA", "A");
        q.put("optionB", "B");
        q.put("optionC", "C");
        q.put("optionD", "D");
        q.put("marks", 2);

        resp.put("questions", List.of(q));
        return resp;
    }

    public StudentAnswer submitAnswer(Long studentExamId, Long questionId, String selectedOption) {
        StudentAnswer ans = new StudentAnswer();
        ans.setAnswerId(200L);
        ans.setSelectedOption(selectedOption);
        ans.setIsCorrect("B".equalsIgnoreCase(selectedOption));
        ans.setMarksEarned(ans.getIsCorrect() ? 2 : 0);
        return ans;
    }

    public Map<String, Object> completeExam(Long studentExamId) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("studentExamId", studentExamId);
        resp.put("finalScore", 10);
        return resp;
    }

    public Map<String, Object> getResults(Long studentExamId) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("examTitle", "Demo Exam");
        resp.put("description", "Demo description");
        resp.put("score", 5);

        Map<String, Object> q = new HashMap<>();
        q.put("questionId", 1L);
        q.put("questionText", "Sample Question?");
        q.put("optionA", "A");
        q.put("optionB", "B");
        q.put("optionC", "C");
        q.put("optionD", "D");
        q.put("correctOption", "B");
        q.put("marks", 2);
        q.put("selectedOption", "A");
        q.put("isCorrect", false);
        q.put("marksEarned", 0);

        resp.put("questions", List.of(q));
        return resp;
    }
}
