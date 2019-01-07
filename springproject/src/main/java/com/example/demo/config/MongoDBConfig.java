package com.example.demo.config;

import com.example.demo.document.QAnalysisModel;
import com.example.demo.document.QuestionModel;
import com.example.demo.repository.QAnalysisRepository;
import com.example.demo.repository.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import java.util.UUID;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = QuestionRepository.class)
public class MongoDBConfig implements CommandLineRunner {
    private QuestionRepository questionRepository;
    private QAnalysisRepository qAnalysisRepository;
    private String qid;


    public MongoDBConfig(QuestionRepository questionRepository, QAnalysisRepository qAnalysisRepository) {
        this.questionRepository = questionRepository;
        this.qAnalysisRepository = qAnalysisRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.qAnalysisRepository.deleteAll();
//        this.questionRepository.deleteAll();


//        qid = "3794586a-7928-4ca3-9556-cf5d03f1ecaa";
////        QuestionModel questionModel1 = new QuestionModel(
////                 qid,
////                1,
////                2019,
////                "How was the Food?",
////                true
////        );
////        this.questionRepository.save(questionModel1);
//
//        QAnalysisModel qAnalysisModel1 = new QAnalysisModel(
//                UUID.randomUUID().toString(),
//                qid,
//                2,
//                2018,
//                6,
//                1
//        );
//        this.qAnalysisRepository.save(qAnalysisModel1);
//
//        qAnalysisModel1 = new QAnalysisModel(
//                UUID.randomUUID().toString(),
//                qid,
//                6,
//                2018,
//                9,
//                1
//        );
//        this.qAnalysisRepository.save(qAnalysisModel1);
//
//        qAnalysisModel1 = new QAnalysisModel(
//                UUID.randomUUID().toString(),
//                qid,
//                11,
//                2018,
//                10,
//                1
//        );
//        this.qAnalysisRepository.save(qAnalysisModel1);
//
//        qAnalysisModel1 = new QAnalysisModel(
//                UUID.randomUUID().toString(),
//                qid,
//                3,
//                2018,
//                5,
//                1
//        );
//        this.qAnalysisRepository.save(qAnalysisModel1);
//
//        qAnalysisModel1 = new QAnalysisModel(
//                UUID.randomUUID().toString(),
//                qid,
//                4,
//                2018,
//                8,
//                1
//        );
//        this.qAnalysisRepository.save(qAnalysisModel1);




//        List<QuestionModel> questionModels = Arrays.asList(questionModel1);
//        this.questionRepository.saveAll(questionModels);
//        List<QAnalysisModel> qAnalysisModels = Arrays.asList(qAnalysisModel1);
//        this.qAnalysisRepository.saveAll(qAnalysisModels);
    }
}
