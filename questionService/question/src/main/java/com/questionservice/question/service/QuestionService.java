package com.questionservice.question.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.questionservice.question.entity.Question;
import com.questionservice.question.entity.QuestionWrapper;
import com.questionservice.question.entity.QuizResult;
import com.questionservice.question.repository.QuestionRepository;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository repository;

    public ResponseEntity<List<Question>> getQuestions() {
        try {
            return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Question> getQuestion(int id) {
        try {
            Optional<Question> question = repository.findById(id);
            if (question.isPresent()) {
                return new ResponseEntity<>(question.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("wrong data");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Question> postQuestion(Question question) {
        try {
            return new ResponseEntity<>(repository.save(question), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateQuestion(Question question, int id) {
        try {
            Question db = repository.findById(id).get();

            if (!Objects.isNull(question.getQuestionTitle()) && question.getQuestionTitle() != "") {

                db.setQuestionTitle(question.getQuestionTitle());

            }
            if (!Objects.isNull(question.getOption1()) && question.getOption1() != "") {
                db.setOption1(question.getOption1());
            }
            if (!Objects.isNull(question.getOption2()) && question.getOption2() != "") {
                db.setOption2(question.getOption2());
            }
            if (!Objects.isNull(question.getOption2()) && question.getOption3() != "") {
                db.setOption3(question.getOption3());
            }
            if (!Objects.isNull(question.getOption4()) && question.getOption4() != "") {
                db.setOption4(question.getOption4());
            }
            if (!Objects.isNull(question.getRightAnswer()) && question.getRightAnswer() != "") {
                db.setRightAnswer(question.getRightAnswer());
            }
            if (!Objects.isNull(question.getDifficultLevel()) && question.getDifficultLevel() != "") {
                db.setDifficultLevel(question.getDifficultLevel());
            }
            repository.save(db);
            return new ResponseEntity<>("question updated succefully", HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    // private String toUpperCaseOfFirstCharacter(String difficultLevel) {

    // return Character.toString(difficultLevel.charAt(0)).toUpperCase()
    // .concat(difficultLevel.substring(1).toLowerCase());
    // }

    public ResponseEntity<String> deleteQuestion(int id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>("question is deleted", HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByDifficultLevel(String difficulty) {
        List<Question> questions = repository.findAllQuestionByDifficultLevel(difficulty);

        if (questions.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<Question>> postQuestions(List<Question> questions) {
        try {
            return new ResponseEntity<>(repository.saveAll(questions), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteAllQuestion() {

        try {
            repository.deleteAll();
            return new ResponseEntity<String>("questions are deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        List<Question> question = repository.findAllQuestionByCategory(category);
        if (question.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    public ResponseEntity<List<Integer>> generateQUestions(String category, int numberOfQuestion) {
        List<Integer> listOfQuestoinid;
        try {
            listOfQuestoinid = repository.findAllQuestionByCategoryLimit(category, numberOfQuestion);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Integer>>(listOfQuestoinid, HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(List<Integer> listOfQuestionId) {
        List<QuestionWrapper> wrappedQuestions = new ArrayList<>();
        try {

            List<Question> questions = repository.findAllById(listOfQuestionId);

            for (Question question : questions) {
                wrappedQuestions.add(new QuestionWrapper(question.getId(), question.getQuestionTitle(),
                        question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4()));
            }

        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(wrappedQuestions, HttpStatus.OK);
    }

    public Question getQuestionById(int id) {
        return repository.findById(id).get();

    }

    public ResponseEntity<Integer> getScore(List<QuizResult> quizResults) {
        int score = 0;
        try {
            for (QuizResult quiz : quizResults) {

                Question question = getQuestionById(quiz.getQuestion_id());
                if (answerValidation(question, quiz.getUser_answer())) {
                    score++;
                }

            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(score, HttpStatus.OK);
    }

    private Boolean answerValidation(Question question, String user_answer) {

        return question.getRightAnswer().equals(user_answer);
    }

}
