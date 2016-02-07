package ru.thesn.tvs.cas.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect
public class TVSResponse implements Response {
    private String code;
    private String errorMessage;

    @JsonDeserialize(as=ArrayList.class)
    private List<TVChannel> channels;

    public TVSResponse(String code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public TVSResponse(String code) {
        this.code = code;
    }

    public TVSResponse() {
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
