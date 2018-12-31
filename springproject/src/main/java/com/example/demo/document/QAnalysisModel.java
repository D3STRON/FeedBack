package com.example.demo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "QAnalysis")
public class QAnalysisModel {

    @Id
    public String qanalysisid;

    public String questionId;
    public int month;
    public int year;
    public int totalScore;
    public int totalFeedbacks;


    public QAnalysisModel(String qanalysisid, String questionId, int month, int year, int totalScore, int totalFeedbacks) {
        this.qanalysisid = qanalysisid;
        this.questionId = questionId;
        this.month = month;
        this.year = year;
        this.totalScore = totalScore;
        this.totalFeedbacks = totalFeedbacks;
    }



    public QAnalysisModel() {

    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getTotalFeedbacks() {
        return totalFeedbacks;
    }

    public void setTotalFeedbacks(int totalFeedbacks) {
        this.totalFeedbacks = totalFeedbacks;
    }
}
