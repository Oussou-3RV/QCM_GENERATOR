package com.qcm.generator.model;

import java.util.List;

public class QcmResponse {
    private List<Question> questions;

    //Constructeur sans paramètre
    public QcmResponse() {}

    //Constructeur avec paramètre
    public QcmResponse(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
