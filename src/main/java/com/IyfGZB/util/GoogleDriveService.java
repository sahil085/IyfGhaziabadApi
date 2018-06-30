package com.IyfGZB.util;

import com.IyfGZB.domain.Seminar;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class GoogleDriveService {


    public  Drive getDriveService() throws GeneralSecurityException,
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
                .setServiceAccountUser("vermasahil269@gmail.com")
                .setServiceAccountPrivateKeyFromP12File(key)
                .build();
        Drive service = new Drive.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("iskcongzb")
                .build();

      FileList files= service.files().list().execute();
        System.out.println(files);
        return service;
    }

    public boolean uploadFile(Seminar seminar,MultipartFile multipartFile) throws GeneralSecurityException, IOException {
        String folderId = "1zAz4Rw792HvxWAnHmzP9kZMVwEnZ2hMc";
        File fileMetadata = new File();
        fileMetadata.setName("poster"+seminar.getTitle());
        fileMetadata.setParents(Collections.singletonList(folderId));
        File file = getDriveService().files().
                create(fileMetadata, new InputStreamContent("",new ByteArrayInputStream(multipartFile.getBytes())))
                .setFields("id, parents")
                .execute();
//        File file = getDriveService().files().create(fileMetadata, mediaContent)
//                .setFields("id, parents")
//                .execute();
        System.out.println("File ID: " + file.getId());
        return true;
    }



}
