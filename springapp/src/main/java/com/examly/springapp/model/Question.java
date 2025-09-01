package com.examly.springapp.model;


import jakarta.persistence.*;

@Entity
public class Question {
    @Id
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "examId", nullable = false)
    private Exam exam;

    @Column(nullable = false, length = 500)
    private String questionText;

    @Column(nullable = false, length = 200)
    private String optionA;

    @Column(nullable = false, length = 200)
    private String optionB;

    @Column(nullable = false, length = 200)
    private String optionC;

    @Column(nullable = false, length = 200)
    private String optionD;

    @Column(nullable = false, length = 1)
    private String correctOption; // A, B, C, D

    @Column(nullable = false)
    private Integer marks;  // 1-10

    // getters and setters
}
