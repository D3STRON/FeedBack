package com.example.demo.document;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Questions")
public class QuestionModel {
    @Id
    public String questionId;

    public int lastModifiedMonth;
    public int lastModifiedYear;
    public String questionName;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public int getLastModifiedMonth() {
        return lastModifiedMonth;
    }

    public QuestionModel(String questionId, int lastModifiedMonth, int lastModifiedYear, String questionName) {
        this.questionId = questionId;
        this.lastModifiedMonth = lastModifiedMonth;
        this.lastModifiedYear = lastModifiedYear;
        this.questionName = questionName;
    }

    public QuestionModel() {

    }

    public void setLastModifiedMonth(int lastModifiedMonth) {
        this.lastModifiedMonth = lastModifiedMonth;
    }

    public int getLastModifiedYear() {
        return lastModifiedYear;
    }

    public void setLastModifiedYear(int lastModifiedYear) {
        this.lastModifiedYear = lastModifiedYear;
    }


    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }
}
