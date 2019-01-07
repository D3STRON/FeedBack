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
    public boolean visible;

    public QuestionModel(String questionId, int lastModifiedMonth, int lastModifiedYear, String questionName, boolean visible) {
        this.questionId = questionId;
        this.lastModifiedMonth = lastModifiedMonth;
        this.lastModifiedYear = lastModifiedYear;
        this.questionName = questionName;
        this.visible = visible;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public int getLastModifiedMonth() {
        return lastModifiedMonth;
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
