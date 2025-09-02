package com.examly.springapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class StudentExam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentExamId;

    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    private String studentUsername;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Integer score;

    private String status; // NOT_STARTED, IN_PROGRESS, COMPLETED

    @OneToMany(mappedBy = "studentExam", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentAnswer> answers = new ArrayList<>();

    // Getters & Setters
    public Long getStudentExamId() { return studentExamId; }
    public void setStudentExamId(Long studentExamId) { this.studentExamId = studentExamId; }
    public Exam getExam() { return exam; }
    public void setExam(Exam exam) { this.exam = exam; }
    public String getStudentUsername() { return studentUsername; }
    public void setStudentUsername(String studentUsername) { this.studentUsername = studentUsername; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<StudentAnswer> getAnswers() { return answers; }
    public void setAnswers(List<StudentAnswer> answers) { this.answers = answers; }
}
