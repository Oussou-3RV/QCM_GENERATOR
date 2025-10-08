package com.qcm.generator.model;

public class CourseRequest {
    private String courseText;

    public CourseRequest() {
    }

    public CourseRequest(String courseText) {
        this.courseText = courseText;
    }

    public String getCourseText() {
        return courseText;
    }

    public void setCourseText(String courseText) {
        this.courseText = courseText;
    }
}
