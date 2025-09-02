package com.examly.springapp.model;

import jakarta.persistence.*;

@Entity
public class StudentAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @ManyToOne
    @JoinColumn(name = "student_exam_id", nullable = false)
    private StudentExam studentExam;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    private String selectedOption;
    private Boolean isCorrect;

    // Getters & Setters
    public Long getAnswerId() { return answerId; }
    public void setAnswerId(Long answerId) { this.answerId = answerId; }
    public StudentExam getStudentExam() { return studentExam; }
    public void setStudentExam(StudentExam studentExam) { this.studentExam = studentExam; }
    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }
    public String getSelectedOption() { return selectedOption; }
    public void setSelectedOption(String selectedOption) { this.selectedOption = selectedOption; }
    public Boolean getIsCorrect() { return isCorrect; }
    public void setIsCorrect(Boolean isCorrect) { this.isCorrect = isCorrect; }
}
