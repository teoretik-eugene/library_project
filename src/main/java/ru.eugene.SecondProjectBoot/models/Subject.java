package ru.eugene.SecondProjectBoot.models;

import java.util.List;

public abstract class Subject {

    protected String subjectName;

    protected List<String> requiredLiterature;

    protected Subject(String subjectName, List<String> literature){
        this.subjectName = subjectName;
        this.requiredLiterature = literature;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public List<String> getRequiredLiterature() {
        return requiredLiterature;
    }

    public void setRequiredLiterature(List<String> requiredLiterature) {
        this.requiredLiterature = requiredLiterature;
    }

    @Override
    public String toString() {
        return this.subjectName;
    }
}
