package com.jamie.apidoc.swagger;

import lombok.Data;

import java.util.Map;

@Data
public class Definition {

    private String type;
    private Map<String, PropertiesBean> properties;
    private String title;

    @Data
    public static class PropertiesBean {
        private String type;

    }
}
