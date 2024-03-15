package com.haint.springjpa.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseObj<T> {
    private final String statusCode;
    private final List<T> data;
    private final List<String> messages;

    public ResponseObj(ResponseObjBuilder<T> builder) {
        this.statusCode = builder.statusCode;
        this.data = builder.data;
        this.messages = builder.messages;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public List<T> getData() {
        return data;
    }

    public List<String> getMessages() {
        return messages;
    }

    public static class ResponseObjBuilder<T> {
        private String statusCode;
        private List<T> data;
        private List<String> messages = new ArrayList<>();

        public ResponseObjBuilder<T> setStatusCode(String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public ResponseObjBuilder<T> setData(List<T> data) {
            this.data = data;
            return this;
        }

        public ResponseObjBuilder<T> setMessages(List<String> messages) {
            this.messages = messages;
            return this;
        }

        public ResponseObj<T> build() {
            return new ResponseObj<>(this);
        }
    }
}
