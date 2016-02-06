package ru.thesn.tvs.etvsl.model;

import java.util.Set;

public class Response {
    private String code;
    private String errorMessage;
    private Set<TVChannel> channels;


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

    public Set<TVChannel> getChannels() {
        return channels;
    }

    public void setChannels(Set<TVChannel> channels) {
        this.channels = channels;
    }
}
