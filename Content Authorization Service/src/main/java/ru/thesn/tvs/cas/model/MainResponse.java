package ru.thesn.tvs.cas.model;


import java.util.List;

public class MainResponse implements Response {
    private String code;
    private String errorMessage;

    private List<TVChannel> channels;

    public MainResponse(String code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public MainResponse(String code) {
        this.code = code;
    }

    public MainResponse() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<TVChannel> getChannels() {
        return channels;
    }

    public void setChannels(List<TVChannel> channels) {
        this.channels = channels;
    }
}
