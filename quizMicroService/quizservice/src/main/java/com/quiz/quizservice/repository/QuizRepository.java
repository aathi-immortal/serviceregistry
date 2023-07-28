package com.quiz.quizservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.quizservice.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

}
