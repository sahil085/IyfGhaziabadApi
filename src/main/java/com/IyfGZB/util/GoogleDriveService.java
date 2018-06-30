package com.IyfGZB.util;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.FileList;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

@Component
public class GoogleDriveService {


    public  Drive getDriveService(String userEmail) throws GeneralSecurityException,
            IOException {

        java.io.File key = new java.io.File("Iskcongzb-5f95df94ece9.p12");
        System.out.println(key.getAbsolutePath());
        HttpTransport httpTransport = new NetHttpTransport();
        JacksonFactory jsonFactory = new JacksonFactory();
        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(jsonFactory)
                .setServiceAccountId("iconnect@iskcongzb-6cbb6.iam.gserviceaccount.com")
                .setServiceAccountScopes(Arrays.asList(DriveScopes.DRIVE))
                .setServiceAccountUser(userEmail)
                .setServiceAccountPrivateKeyFromP12File(key)
                .build();
        Drive service = new Drive.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("iskcongzb")
                .build();

      FileList files= service.files().list().execute();
        System.out.println(files);
        return service;
    }



}
