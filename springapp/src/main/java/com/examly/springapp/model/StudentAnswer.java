package com.examly.springapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class StudentAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    private String selectedOption;
    private Boolean isCorrect;
    private int marksEarned;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
