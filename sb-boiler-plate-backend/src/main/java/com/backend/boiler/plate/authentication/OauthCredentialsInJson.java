package com.backend.boiler.plate.authentication;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.security.web.savedrequest.Enumerator;

public class OauthCredentialsInJson extends HttpServletRequestWrapper implements Serializable {
    private final HashMap<String, String[]> params;

    public OauthCredentialsInJson(HttpServletRequest request, HashMap<String, String[]> params) {
        super(request);
        this.params = params;
    }

    @Override
    public String getParameter(String name) {
        if (this.params.containsKey(name)) {
            return this.params.get(name)[0];
        }
        return "";
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return this.params;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return new Enumerator<>(params.keySet());
    }

    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }
}

