package com.examly.springapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    @NotBlank @Size(max = 500)
    private String questionText;

    @NotBlank @Size(max = 200)
    private String optionA;

    @NotBlank @Size(max = 200)
    private String optionB;

    @NotBlank @Size(max = 200)
    private String optionC;

    @NotBlank @Size(max = 200)
    private String optionD;

    @Pattern(regexp = "A|B|C|D")
    private String correctOption;

    @Min(1) @Max(10)
    private Integer marks;

    // Getters & Setters
    public Long getQuestionId() { return questionId; }
    public void setQuestionId(Long questionId) { this.questionId = questionId; }
    public Exam getExam() { return exam; }
    public void setExam(Exam exam) { this.exam = exam; }
    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }
    public String getOptionA() { return optionA; }
    public void setOptionA(String optionA) { this.optionA = optionA; }
    public String getOptionB() { return optionB; }
    public void setOptionB(String optionB) { this.optionB = optionB; }
    public String getOptionC() { return optionC; }
    public void setOptionC(String optionC) { this.optionC = optionC; }
    public String getOptionD() { return optionD; }
    public void setOptionD(String optionD) { this.optionD = optionD; }
    public String getCorrectOption() { return correctOption; }
    public void setCorrectOption(String correctOption) { this.correctOption = correctOption; }
    public Integer getMarks() { return marks; }
    public void setMarks(Integer marks) { this.marks = marks; }
}
