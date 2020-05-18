package com.eastsenic.security.SecurityService.multifactorauth.model.viewmodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {
    private int responseCode;
    private List<String> responseMessages;
    private T items;

    public Response() {
        this.responseMessages = new ArrayList<>();
    }

    public Response(T items) {
        this.items = items;
        this.responseMessages = new ArrayList<>();
    }

}
