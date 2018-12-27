package com.example.demo;

import com.example.demo.document.QAnalysisModel;
import com.example.demo.document.QuestionModel;
import com.example.demo.repository.QAnalysisRepository;
import com.example.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class FeedBackController {
    private final QuestionRepository questionRepository;
    protected final QAnalysisRepository qAnalysisRepository;
    Calendar c = Calendar.getInstance();
    String qid;

    @Autowired
    public FeedBackController(QuestionRepository questionRepository, QAnalysisRepository qAnalysisRepository) {
        this.questionRepository = questionRepository;
        this.qAnalysisRepository = qAnalysisRepository;
    }

    @CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
    @RequestMapping(value = "/login", method = POST)
    public int login()
    {
        return 200;
    }

    @CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
    @RequestMapping(value = "/getQuestions", method = RequestMethod.GET)
    public List<QuestionModel> getQuestions()
    {
        List<QuestionModel> questionModels = this.questionRepository.findAll();
        return questionModels;
    }

    @CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
    @RequestMapping(value = "/addQuestion",  method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public int addQuestion(HttpEntity<Map<String,String>> httpEntity)
    {
        qid = UUID.randomUUID().toString();
        this.questionRepository.save(new QuestionModel(
                qid,
                c.get(Calendar.MONTH),
                c.get(Calendar.YEAR),
                httpEntity.getBody().get("question")
        ));

        this.qAnalysisRepository.save(new QAnalysisModel(
                UUID.randomUUID().toString(),
                qid,
                c.get(Calendar.MONTH),
                c.get(Calendar.YEAR),
                0,
                1
        ));

        return 200;
    }
}
