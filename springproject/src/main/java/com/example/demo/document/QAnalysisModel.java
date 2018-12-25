package com.example.demo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "QAnalysis")
public class QAnalysisModel {

    @Id
    public String qanalysisid;

    public String questionId;
    public int Month;
    public int Year;
    public float totalScore;
    public int totalFeedbacks;


    public QAnalysisModel(String qanalysisid, String questionId, int month, int year, float totalScore, int totalFeedbacks) {
        this.qanalysisid = qanalysisid;
        this.questionId = questionId;
        Month = month;
        Year = year;
        this.totalScore = totalScore;
        this.totalFeedbacks = totalFeedbacks;
    }


    public QAnalysisModel(String qanalysisid) {
        this.qanalysisid = qanalysisid;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public float getTotalScore() {
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
