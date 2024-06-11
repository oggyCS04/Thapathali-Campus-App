package com.sagar.thapathaliapp2;

public class ClassItem {
    private final String teacherName;
    private final String subject;

    public String getTeacherName() {
        return teacherName;
    }

    public String getSubject() {
        return subject;
    }

    public ClassItem(String teacherName, String subject) {
        this.teacherName = teacherName;
        this.subject = subject;
    }
}
