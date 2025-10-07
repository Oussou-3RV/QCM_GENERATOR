package com.qcm.generator.model;

public class TestResponse {
    private String message;
    private int textLength;

    //constructeur vide
    public TestResponse() {}

    //constructeur avec paramètre
    public TestResponse(String message, int textLength) {
        this.message = message;
        this.textLength = textLength;
    }
    //Getter
    public String getMessage() {
        return message;
    }
    //Setter
    public void setMessage(String message) {
        this.message = message;
    }
    //Getter for textLength
    public int getTextLength() {
        return textLength;
    }
    //Setter for textLength
    public void setTextLength(int textLength) {
        this.textLength = textLength;
    }
}
