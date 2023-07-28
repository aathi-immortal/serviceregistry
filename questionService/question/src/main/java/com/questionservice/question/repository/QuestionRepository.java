package com.questionservice.question.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.questionservice.question.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findAllQuestionByDifficultLevel(String difficulty);

    List<Question> findAllQuestionByCategory(String category);

    @Query(value = "SELECT id FROM question  WHERE category=:category ORDER BY RANDOM() LIMIT :limit", nativeQuery = true)
    List<Integer> findAllQuestionByCategoryLimit(String category, int limit);

}
