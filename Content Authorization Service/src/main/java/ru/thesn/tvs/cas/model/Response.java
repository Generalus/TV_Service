package ru.thesn.tvs.cas.model;


public interface Response {
    String getCode();
    void setCode(String code);
    String getErrorMessage();
    void setErrorMessage(String errorMessage);
}
