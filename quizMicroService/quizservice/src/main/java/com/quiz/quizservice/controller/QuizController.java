package com.quiz.quizservice.controller;

import com.quiz.quizservice.entity.QuestionWrapper;
import com.quiz.quizservice.entity.Quiz;
import com.quiz.quizservice.entity.QuizDto;
import com.quiz.quizservice.entity.QuizResult;
import com.quiz.quizservice.service.QuizService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService service;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
        return service.createQuiz(quizDto);
    }

    @GetMapping("getQuiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable("id") int id) {
        return service.getQuiz(id);
    }

    // @GetMapping("getAllQuiz")
    // public ResponseEntity<List<Quiz>> getAllQuiz() {
    // return service.getAllQuiz();
    // }

    @PostMapping("submit/{id}")
    public ResponseEntity<String> submitQuiz(@RequestBody List<QuizResult> quizResult, @PathVariable int id) {
        System.out.println(quizResult);
        return service.submitQuiz(quizResult, id);
    }
}
