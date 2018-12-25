package com.example.demo;

import com.example.demo.document.QuestionModel;
import com.example.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeedBackController {
    @Autowired
    private QuestionRepository questionRepository;

    @CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public int login()
    {
        return 200;
    }

    @CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
    @RequestMapping(value = "/feedback", method = RequestMethod.GET)
    public List<QuestionModel> getQuestions()
    {
        List<QuestionModel> questionModels = this.questionRepository.findAll();
//        List<QuestionModel> questionModels = new ArrayList<>();
//        questionModels.add(new QuestionModel("How was the Food?",0,0));
//        questionModels.add(new QuestionModel("How was the Punctuality?",0,0));
//        questionModels.add(new QuestionModel("How was the Over All Experience?",0,0));
        return questionModels;
    }
}
