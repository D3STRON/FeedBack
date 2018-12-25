package com.example.demo.config;

import com.example.demo.document.QAnalysisModel;
import com.example.demo.document.QuestionModel;
import com.example.demo.repository.QAnalysisRepository;
import com.example.demo.repository.QuestionRepository;
import org.apache.logging.log4j.LogManager;
import org.bson.types.ObjectId;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = QuestionRepository.class)
public class MongoDBConfig implements CommandLineRunner {
    private QuestionRepository questionRepository;
    private QAnalysisRepository qAnalysisRepository;


    public MongoDBConfig(QuestionRepository questionRepository, QAnalysisRepository qAnalysisRepository) {
        this.questionRepository = questionRepository;
        this.qAnalysisRepository = qAnalysisRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        QuestionModel questionModel1 = new QuestionModel(
                "1",
                2,
                2019,
                "How was the Food?"
        );
        QAnalysisModel qAnalysisModel1 = new QAnalysisModel(
                "1",
                "1",
                2,
                2019,
                0,
                1
        );

        QuestionModel questionModel2 = new QuestionModel(
                "2",
                3,
                2019,
                "How was the Punctuality?"
        );
        QAnalysisModel qAnalysisModel2 = new QAnalysisModel(
                "2",
                "2",
                3,
                2019,
                0,
                1
        );


        QuestionModel questionModel3 = new QuestionModel(
                "3",
                4,
                2019,
                "How was the OverAll Experience?"
        );
        QAnalysisModel qAnalysisModel3 = new QAnalysisModel(
                "3",
                "3",
                4,
                2019,
                0,
                1
        );

        QuestionModel questionModel4 = new QuestionModel(
                "4",
                4,
                2019,
                "How relevant were the questions?"
        );
        QAnalysisModel qAnalysisModel4 = new QAnalysisModel(
                "4",
                "4",
                4,
                2019,
                0,
                1
        );


        this.qAnalysisRepository.deleteAll();
        this.questionRepository.deleteAll();

        //now insert the elements
        List<QuestionModel> questionModels = Arrays.asList(questionModel1,questionModel2,questionModel3,questionModel4);
        this.questionRepository.saveAll(questionModels);
        List<QAnalysisModel> qAnalysisModels = Arrays.asList(qAnalysisModel1,qAnalysisModel2,qAnalysisModel3,qAnalysisModel4);
        this.qAnalysisRepository.saveAll(qAnalysisModels);
    }
}
