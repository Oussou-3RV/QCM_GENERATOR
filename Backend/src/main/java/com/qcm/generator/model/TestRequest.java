package com.qcm.generator.model;

public class TestRequest {
    private String text;

    //constructeur vide
    public TestRequest() {}

    //constructeur avec paramètre
    public TestRequest(String text) {
        this.text = text;
    }
    //Getter
    public String getText() {
        return text;
    }

    //setter
    public void setText(String text) {
        this.text = text;
    }
}
