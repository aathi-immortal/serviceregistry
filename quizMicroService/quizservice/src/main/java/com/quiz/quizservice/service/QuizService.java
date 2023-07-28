package com.quiz.quizservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.quizservice.entity.QuestionWrapper;
import com.quiz.quizservice.entity.Quiz;
import com.quiz.quizservice.entity.QuizDto;
import com.quiz.quizservice.entity.QuizResult;
import com.quiz.quizservice.feign.QuizInterface;
import com.quiz.quizservice.repository.QuizRepository;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuizInterface quizInterface;
    // QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(QuizDto quizDto) {

        // List<Question> question =
        // questionRepository.findAllQuestionByCategoryLimit(quizDto.category,
        // quizDto.numberOfQuestion);
        // Quiz quiz = new Quiz();
        // quiz.setTitle(quizDto.title);
        // quiz.setQuestions(question);
        // quizRepository.save(quiz);
        // get the list of question id from the question service
        // we use resttemplate to do it

        List<Integer> questionId = quizInterface.generate(quizDto.category, quizDto.numberOfQuestion).getBody();
        Quiz quiz = new Quiz();
        quiz.setQuestionsId(questionId);
        quiz.setTitle(quizDto.title);
        quizRepository.save(quiz);
        return new ResponseEntity<>("quiz created successfully", HttpStatus.CREATED);
    }

    // public List<QuestionWrapper> wrapper(List<Question> questions) {

    // quiz wrapper contain all wrapped questoin

    // List<QuestionWrapper> quizWrapper = new ArrayList<>();

    // for (Question question : questions) {
    // QuestionWrapper questionWrapper = new QuestionWrapper(question.getId(),
    // question.getQuestionTitle(),
    // question.getOption1(),
    // question.getOption2(), question.getOption3(), question.getOption4());

    // quizWrapper.add(questionWrapper);
    // }
    // return quizWrapper;
    // }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {

        // List<Question> questions;
        // try {

        // Optional<Quiz> quiz = quizRepository.findById(id);
        // if (quiz.isPresent()) {
        // questions = quiz.get().getQuestions();

        // return new ResponseEntity<>(wrapper(questions), HttpStatus.OK);
        // }
        // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // } catch (Exception e) {
        // // TODO: handle exception
        // e.printStackTrace();
        // }
        // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        // }

        // public ResponseEntity<List<Quiz>> getAllQuiz() {
        // try {
        // List<Quiz> quizs = quizRepository.findAll();
        // if (quizs.isEmpty())
        // return new ResponseEntity<List<Quiz>>(HttpStatus.NOT_FOUND);
        // return new ResponseEntity<List<Quiz>>(quizs, HttpStatus.OK);
        // } catch (Exception e) {
        // // TODO: handle exception
        // e.printStackTrace();
        // }
        return new ResponseEntity<List<QuestionWrapper>>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> submitQuiz(List<QuizResult> quizResults, int id) {

        // try {
        // Optional<Quiz> quiz = quizRepository.findById(id);
        // if (quiz.isPresent()) {

        // List<Question> questions = quiz.get().getQuestions();
        // return new ResponseEntity<>("Your score is " + answerChecker(questions,
        // quizResults), HttpStatus.OK);
        // // }
        // }
        // return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        // } catch (Exception e) {
        // // TODO: handle exception
        // e.printStackTrace();
        // }
        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }
}
// public HashMap<Integer, Question> cacheTheQuestion(List<Question> questions)
// {
// HashMap<Integer, Question> questionHashMap = new HashMap<>();
// for (Question question : questions) {
// questionHashMap.put(question.getId(), question);
// }
// return questionHashMap;
// }

// public Integer answerChecker(List<Question> questions, List<QuizResult>
// quizResults) {

// int result = 0;
// HashMap<Integer, Question> questionHashMap = cacheTheQuestion(questions);

// for (QuizResult quizResult : quizResults) {
// Question question = questionHashMap.get(quizResult.getQuestion_id());
// if
// (question.getRightAnswer().compareToIgnoreCase(quizResult.getUser_answer())
// == 0) {
// result++;
// }
// }
// return result;

// }
