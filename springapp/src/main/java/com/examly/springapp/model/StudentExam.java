package com.examly.springapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class StudentExam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentExamId;

    private String studentUsername;
    private int finalScore;

    @ManyToOne
    private Exam exam;

    @OneToMany(cascade = CascadeType.ALL)
    private List<StudentAnswer> answers;
}
