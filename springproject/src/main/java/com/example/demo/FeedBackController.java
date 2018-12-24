package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FeedBackController {

    @CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public int login()
    {
        return 200;
    }

    @CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
    @RequestMapping(value = "/feedback", method = RequestMethod.GET)
    public ArrayList<QuestionModel> getQuestions()
    {
        ArrayList<QuestionModel> questionModels = new ArrayList<>();
        questionModels.add(new QuestionModel("How was the Food?"));
        questionModels.add(new QuestionModel("How was the Punctuality?"));
        questionModels.add(new QuestionModel("How was the Over All Experience?"));
        return questionModels;
    }
}
