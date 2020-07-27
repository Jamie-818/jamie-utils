package com.jamie.apidoc.swagger;

import lombok.Data;

/**
 * info
 */
@Data
public class Info {
    private String description;
    private String version;
    private String title;
    private ContactBean contact;
    private LicenseBean license;

    @Data
    public static class ContactBean {
    }

    @Data
    public static class LicenseBean {
        private String name;
        private String url;
    }
}
