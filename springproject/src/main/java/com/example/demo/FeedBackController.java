package com.example.demo;

import com.example.demo.document.QAnalysisModel;
import com.example.demo.document.QuestionModel;
import com.example.demo.repository.QAnalysisRepository;
import com.example.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
    @RequestMapping(value = "/questions/all", method = RequestMethod.GET)
    public List<QuestionModel> getQuestions()
    {
        List<QuestionModel> questionModels = this.questionRepository.findAll();
        return questionModels;
    }


    @RequestMapping(value = "/feedback",  method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
    public int addFeedback(HttpEntity<HashMap<String, Object>> httpEntity)
    {

        ArrayList<HashMap<String,Object>> feedbacks =((ArrayList<HashMap<String,Object>>) httpEntity.getBody().get("questionsAttempted"));
        for(int i=0; i<feedbacks.size(); i++)
        {
            QuestionModel temp =this.questionRepository.findQuestionModelByQuestionName(feedbacks.get(i).get("questionName").toString());
            if(temp.getLastModifiedMonth()==c.get(Calendar.MONTH)
             && temp.getLastModifiedYear()==c.get(Calendar.YEAR))
            {
                QAnalysisModel tempanalysis = this.qAnalysisRepository.findQAnalysisModelByQuestionIdAndMonthAndYear(temp.getQuestionId(),temp.getLastModifiedMonth(),temp.getLastModifiedYear());
                tempanalysis.setTotalScore(tempanalysis.getTotalScore()+Integer.parseInt(feedbacks.get(i).get("questionRating").toString()));
                tempanalysis.setTotalFeedbacks(tempanalysis.getTotalFeedbacks()+1);
                this.qAnalysisRepository.save(tempanalysis);
            }
            else
            {
                temp.setLastModifiedMonth(c.get(Calendar.MONTH));
                temp.setLastModifiedYear(c.get(Calendar.YEAR));
                this.questionRepository.save(temp);
                QAnalysisModel tempanalysis = new QAnalysisModel(
                        UUID.randomUUID().toString(),
                        temp.getQuestionId(),
                        temp.getLastModifiedMonth(),
                        temp.getLastModifiedYear(),
                        0,
                        0
                );
            }
        }
        return 200;
    }


    @RequestMapping(value = "/addQuestion",  method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
    public int addQuestion(HttpEntity<Map<String,String>> httpEntity)
    {
        qid = UUID.randomUUID().toString();
        this.questionRepository.save(new QuestionModel(
                qid,
                c.get(Calendar.MONTH),
                c.get(Calendar.YEAR),
                httpEntity.getBody().get("questionName")
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
