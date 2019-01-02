package com.example.demo.repository;

import com.example.demo.document.QAnalysisModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QAnalysisRepository  extends MongoRepository<QAnalysisModel, String> {
    QAnalysisModel findQAnalysisModelByQuestionIdAndMonthAndYear(String questionId, int month, int year);

    List<QAnalysisModel> findQAnalysisModelsByQuestionIdAndYear(String questionId, int year);
}
