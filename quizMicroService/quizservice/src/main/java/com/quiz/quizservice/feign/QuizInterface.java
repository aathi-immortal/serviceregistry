package com.quiz.quizservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.quiz.quizservice.entity.QuestionWrapper;
import com.quiz.quizservice.entity.QuizResult;

/**
 * QuizInterface
 */
@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> generate(@RequestParam(name = "category") String category,
            @RequestParam(name = "numberOfQuestion") int numberOfQuestion);

    @PostMapping("question/quiz/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(@RequestBody List<Integer> listOfQuestionId);

    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<QuizResult> quizResults);

}