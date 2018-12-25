package com.example.demo.config;

import com.example.demo.document.QuestionModel;
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


    public MongoDBConfig(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        QuestionModel questionModel1 = new QuestionModel(
                "How was the Food?"
        );
//        QuestionModel questionModel2 = new QuestionModel(
//                "How was the Punctuality?",
//                0,
//                0
//        );
//        QuestionModel questionModel3 = new QuestionModel(
//                "How was the OverAll Experience?",
//                0,
//                0
//        );

        // if you want ot clear the database first
        System.out.println("Hello From MyCommandLineRunner");
//        this.questionRepository.deleteAll();

        //now insert the elements
        List<QuestionModel> questionModels = Arrays.asList(questionModel1);
        this.questionRepository.saveAll(questionModels);
    }
}
