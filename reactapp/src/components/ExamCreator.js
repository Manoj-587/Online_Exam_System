CREATE TABLE Exam (
    examId BIGINT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    duration INT CHECK (duration >= 10 AND duration <= 180),
    createdBy VARCHAR(100) NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    isActive BOOLEAN NOT NULL
);
