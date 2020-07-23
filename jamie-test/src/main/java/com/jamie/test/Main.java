package com.jamie.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final List<String> EXCLUDE_PARAMS =
        new ArrayList<>(Arrays.asList("bankAccountPhotos", "powerOfAttorneyImg", "photoInfos", "appendixId",
            "certificatePhotos", "propertyCertificatePhotos", "accountHolderPhotoInfos", "accountPhotoInfos"));
    private static final List<String> EXCLUDE_PARAM =
        Arrays.asList("bankAccountPhotos", "powerOfAttorneyImg", "photoInfos", "appendixId", "certificatePhotos",
            "propertyCertificatePhotos", "accountHolderPhotoInfos", "accountPhotoInfos");

    public static void main(String[] args) {
        System.out.println(Main.EXCLUDE_PARAMS);
        System.out.println(Main.EXCLUDE_PARAM);
    }
}
