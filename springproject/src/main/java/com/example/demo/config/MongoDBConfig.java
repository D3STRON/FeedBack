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
//        qid = UUID.randomUUID().toString();
//        QuestionModel questionModel1 = new QuestionModel(
//                 qid,
//                2,
//                2018,
//                "How was the Food?"
//        );
//        QAnalysisModel qAnalysisModel1 = new QAnalysisModel(
//                UUID.randomUUID().toString(),
//                qid,
//                2,
//                2018,
//                0,
//                1
//        );

//        qid = UUID.randomUUID().toString();
//        QuestionModel questionModel2 = new QuestionModel(
//                qid,
//                3,
//                2019,
//                "How was the Punctuality?"
//        );
//        QAnalysisModel qAnalysisModel2 = new QAnalysisModel(
//                UUID.randomUUID().toString(),
//                qid,
//                3,
//                2019,
//                0,
//                1
//        );
//
//        qid = UUID.randomUUID().toString();
//        QuestionModel questionModel3 = new QuestionModel(
//                qid,
//                4,
//                2019,
//                "How was the OverAll Experience?"
//        );
//        QAnalysisModel qAnalysisModel3 = new QAnalysisModel(
//                UUID.randomUUID().toString(),
//                qid,
//                4,
//                2019,
//                0,
//                1
//        );
//
//        qid = UUID.randomUUID().toString();
//        QuestionModel questionModel4 = new QuestionModel(
//                qid,
//                4,
//                2019,
//                "How relevant were the questions?"
//        );
//        QAnalysisModel qAnalysisModel4 = new QAnalysisModel(
//                UUID.randomUUID().toString(),
//                qid,
//                4,
//                2019,
//                0,
//                1
//        );


//        this.qAnalysisRepository.deleteAll();
//        this.questionRepository.deleteAll();


//        List<QuestionModel> questionModels = Arrays.asList(questionModel1);
//        this.questionRepository.saveAll(questionModels);
//        List<QAnalysisModel> qAnalysisModels = Arrays.asList(qAnalysisModel1);
//        this.qAnalysisRepository.saveAll(qAnalysisModels);
    }
}
