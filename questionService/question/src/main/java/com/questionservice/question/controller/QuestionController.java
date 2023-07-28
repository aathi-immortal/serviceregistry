package com.questionservice.question.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.questionservice.question.entity.Question;
import com.questionservice.question.entity.QuestionWrapper;
import com.questionservice.question.entity.QuizResult;
import com.questionservice.question.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService service;

    @GetMapping("getQuestions")
    public ResponseEntity<List<Question>> getQuestions() {
        return service.getQuestions();
    }

    @GetMapping("/getQuestion/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable("id") int id) {
        return service.getQuestion(id);
    }

    @GetMapping("/getQuestion/byDifficult/{difficulty}")
    public ResponseEntity<List<Question>> getQuestionsByDifficultLevel(@PathVariable("difficulty") String difficulty) {
        return service.getQuestionsByDifficultLevel(difficulty);
    }

    @GetMapping("getQuestion/byCategory/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable("category") String category) {
        return service.getQuestionByCategory(category);
    }

    @PostMapping("addQuestion")
    public ResponseEntity<Question> postQuestion(@RequestBody Question question) {
        return service.postQuestion(question);
    }

    @PostMapping("addAllQuestion")
    public ResponseEntity<List<Question>> postQuestions(@RequestBody List<Question> questions) {
        return service.postQuestions(questions);
    }

    @PutMapping("/updateQuestion/{id}")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question, @PathVariable("id") int id) {
        return service.updateQuestion(question, id);
    }

    @DeleteMapping("/deleteQuestion/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("id") int id) {
        return service.deleteQuestion(id);
    }

    @DeleteMapping("deleteAll")
    public ResponseEntity<String> deleteAllQuestion() {
        return service.deleteAllQuestion();
    }

    // generate
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> generate(@RequestParam(name = "category") String category,
            @RequestParam(name = "numberOfQuestion") int numberOfQuestion) {
        return service.generateQUestions(category, numberOfQuestion);
    }

    @PostMapping("quiz/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(@RequestBody List<Integer> listOfQuestionId) {
        return service.getQuestionsForQuiz(listOfQuestionId);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<QuizResult> quizResults) {
        return service.getScore(quizResults);
    }
}
