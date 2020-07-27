package com.jamie.apidoc.swagger;

import java.util.List;

import lombok.Data;

/**
 * path对象
 */
@Data
public class Path {

    private PostBean post;

    @Data
    public static class PostBean {
        private String summary;
        private String description;
        private String operationId;
        private Object responses;
        private boolean deprecated;
        private List<String> tags;
        private List<String> consumes;
        private List<String> produces;
        private List<ParametersBean> parameters;

        @Data
        public static class ParametersBean {
            private String in;
            private String name;
            private String description;
            private boolean required;
            private Object schema;

        }
    }
}
