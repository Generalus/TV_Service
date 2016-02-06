package ru.thesn.tvs.etvsl.model;

import java.util.List;

public class Response {
    private String code;
    private String errorMessage;
    private List<TVChannel> channels;


    public Response(String code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public Response(String code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<TVChannel> getChannels() {
        return channels;
    }

    public void setChannels(List<TVChannel> channels) {
        this.channels = channels;
    }
}
