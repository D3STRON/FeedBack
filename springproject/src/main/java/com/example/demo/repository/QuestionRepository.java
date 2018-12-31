package com.example.demo.repository;

import com.example.demo.document.QuestionModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<QuestionModel, String> {
    QuestionModel findQuestionModelByQuestionName(String questionName);
}
