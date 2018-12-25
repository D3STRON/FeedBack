package com.example.demo.repository;

import com.example.demo.document.QAnalysisModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QAnalysisRepository  extends MongoRepository<QAnalysisModel, String> {

}
