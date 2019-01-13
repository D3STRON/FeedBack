package com.example.demo;

import com.example.demo.config.JWTConfig;
import com.example.demo.document.QAnalysisModel;
import com.example.demo.document.QuestionModel;
import com.example.demo.repository.QAnalysisRepository;
import com.example.demo.repository.QuestionRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;



import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class FeedBackController {
    private final QuestionRepository questionRepository;
    protected final QAnalysisRepository qAnalysisRepository;
    Calendar c = Calendar.getInstance();
    String qid;
    JWTConfig jwtConfig = new JWTConfig();

    @Autowired
    public FeedBackController(QuestionRepository questionRepository, QAnalysisRepository qAnalysisRepository) {
        this.questionRepository = questionRepository;
        this.qAnalysisRepository = qAnalysisRepository;
    }

    @CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
    @RequestMapping(value = "/login", method = POST)
    public HashMap<String,Object> login(HttpEntity<HashMap<String, String>> httpEntity)
    {
        HashMap<String,Object> response = new HashMap<>();
        if(httpEntity.getBody().get("refId").equalsIgnoreCase("myid") && httpEntity.getBody().get("password").equalsIgnoreCase("mypass"))
        {
            jwtConfig.setId("myid");
            jwtConfig.setSubject("candidate");
            response.put("success",true);
            response.put("tokenType","CNtoken");
            response.put("token",jwtConfig.createJWT());
        }
        else{
            response.put("success",false);
        }
//        System.out.println(jwtConfig.parseJWT(response.get("token").toString()).getExpiration()+" "+response.get("token"));
        return response;
    }

    @CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
    @RequestMapping(value = "/questions/all", method = RequestMethod.GET)
    public List<QuestionModel> getQuestions(@RequestHeader(value="Authorization") String JWTtoken)
    {
//         System.out.println(JWTtoken);
        Claims claims = jwtConfig.parseJWT(JWTtoken); /// checking for the token validity
        if(claims.getSubject().equalsIgnoreCase("candidate") && claims.getExpiration().after(new Date( System.currentTimeMillis())))
        {
            List<QuestionModel> questionModels = this.questionRepository.findAll();
            return questionModels;
        }
        else return null;
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
                        Integer.parseInt(feedbacks.get(i).get("questionRating").toString()),
                        1
                );
                this.qAnalysisRepository.save(tempanalysis);
            }
        }
        return 200;
    }


    @RequestMapping(value = "/feedback/monthly", method = RequestMethod.GET)
    @CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
    public HashMap<String,HashMap<String,Double>> getMonthlyyAnalysis(@RequestParam("year") int year)
    {
        HashMap<String,HashMap<String,Double>> hashMapHashMap = new HashMap<>();
        List<QuestionModel> questionModels = this.questionRepository.findAll();
        for(int i =0; i < 12; i++)
        {
            HashMap<String,Double> hashMap = new HashMap<>();
            for(int j=0 ;j<questionModels.size(); j++)
            {
                QAnalysisModel qAnalysisModel = this.qAnalysisRepository.findQAnalysisModelByQuestionIdAndMonthAndYear(questionModels.get(j).getQuestionId(),i,year);
                if(qAnalysisModel != null)
                {
                    hashMap.put(Integer.toString(j+1),(double)qAnalysisModel.getTotalScore()/qAnalysisModel.getTotalFeedbacks());
                }
                else{
                    hashMap.put(Integer.toString(j+1),0.0);
                }
            }
            hashMapHashMap.put(Integer.toString(i+1),hashMap);
        }
        return hashMapHashMap;
    }


    @RequestMapping(value = "/feedback/yearly", method = RequestMethod.GET)
    @CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
    public HashMap<String,HashMap<String,Double>> getYearlyAnalysis()
    {
        HashMap<String,HashMap<String,Double>> hashMapHashMap = new HashMap<>();
        List<QuestionModel> questionModels = this.questionRepository.findAll();
        for(int i =2017; i <= c.get(Calendar.YEAR); i++)
        {
            HashMap<String,Double> hashMap = new HashMap<>();
            for(int j=0 ;j<questionModels.size(); j++)
            {
                List<QAnalysisModel> qAnalysisModels = this.qAnalysisRepository.findQAnalysisModelsByQuestionIdAndYear(questionModels.get(j).getQuestionId(),i);
                if(!qAnalysisModels.isEmpty())
                {
                    double avg =0.0;
                    for(int k=0; k<qAnalysisModels.size();k++)
                    {
                        avg += (double) qAnalysisModels.get(k).getTotalScore()/qAnalysisModels.get(k).getTotalFeedbacks();
                    }
                    avg /= qAnalysisModels.size();
                    hashMap.put(Integer.toString(j+1),avg);
                }
                else{
                    hashMap.put(Integer.toString(j+1),0.0);
                }
            }
            hashMapHashMap.put(Integer.toString(i),hashMap);
        }
        return hashMapHashMap;
    }

    @RequestMapping(value = "/addQuestion",  method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
    public int addQuestion(HttpEntity<Map<String,String>> httpEntity)
    {
        qid = UUID.randomUUID().toString();
        this.questionRepository.save(new QuestionModel(
                qid,
                3,//c.get(Calendar.MONTH),
                2017,//c.get(Calendar.YEAR),
                httpEntity.getBody().get("questionName"),
                true
        ));

        this.qAnalysisRepository.save(new QAnalysisModel(
                UUID.randomUUID().toString(),
                qid,
                3,//c.get(Calendar.MONTH),
                2017,//c.get(Calendar.YEAR),
                0,
                1
        ));

        return 200;
    }

}
