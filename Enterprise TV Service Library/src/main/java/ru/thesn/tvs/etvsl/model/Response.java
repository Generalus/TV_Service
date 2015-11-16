package ru.thesn.tvs.etvsl.model;

public class Response {
    private String message;
    private TVPackage tvPackage;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TVPackage getTvPackage() {
        return tvPackage;
    }

    public void setTvPackage(TVPackage tvPackage) {
        this.tvPackage = tvPackage;
    }
}
