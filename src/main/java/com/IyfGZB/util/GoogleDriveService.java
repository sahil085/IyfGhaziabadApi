package com.IyfGZB.util;

import com.IyfGZB.domain.Seminar;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class GoogleDriveService {



    public  Drive getDriveService() throws GeneralSecurityException,
            IOException {
        InputStream input = new URL("http://github.com/sahil085/IyfGhaziabadApi/blob/JuneQa/Iskcongzb-0f45852524c6.p12").openStream();
        URL url = new URL("http://github.com/sahil085/IyfGhaziabadApi/blob/JuneQa/Iskcongzb-0f45852524c6.p12");
        java.io.File key = new java.io.File("Iskcongzb-0f45852524c6.p12");

          if(!key.exists()){
              OutputStream out=new FileOutputStream(key);
              byte[] buf=new byte[1024];
              int len;
              while((len=input.read(buf))>0)
                  out.write(buf,0,len);
              out.close();
              input.close();
            }


//        java.io.File key = null;
//        try {
//            key = Paths.get(url.toURI()).toFile();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
        System.out.println(key.getAbsolutePath());
        HttpTransport httpTransport = new NetHttpTransport();
        JacksonFactory jsonFactory = new JacksonFactory();
        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(jsonFactory)
                .setServiceAccountId("drive-441@iskcongzb-6cbb6.iam.gserviceaccount.com")
                .setServiceAccountScopes(Arrays.asList(DriveScopes.DRIVE, SheetsScopes.SPREADSHEETS))
                .setServiceAccountUser("")
                .setServiceAccountPrivateKeyFromP12File(key)
                .build();
        Drive service = new Drive.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("iskcongzb")
                .build();
        BatchRequest batch = service.batch();

        return service;
    }

    public String uploadFile(Seminar seminar,MultipartFile multipartFile) throws GeneralSecurityException, IOException {
        String folderId = "1zAz4Rw792HvxWAnHmzP9kZMVwEnZ2hMc";
        File fileMetadata = new File();
        fileMetadata.setName("poster"+seminar.getTitle());
        fileMetadata.setParents(Collections.singletonList(folderId));
        File file = getDriveService().files().
                 create(fileMetadata, new InputStreamContent("",new ByteArrayInputStream(multipartFile.getBytes())))
                .setFields("id, parents")
                .execute();
        return "https://drive.google.com/uc?id="+file.getId();
    }

    public String uploadAttendanceExcelSheet(String fileName,byte[] fileData) throws GeneralSecurityException, IOException {
        String folderId = "1zAz4Rw792HvxWAnHmzP9kZMVwEnZ2hMc";
        File fileMetadata = new File();
        fileMetadata.setName(fileName);
        fileMetadata.setParents(Collections.singletonList(folderId));
        File file = getDriveService().files().
                create(fileMetadata, new InputStreamContent("",new ByteArrayInputStream(fileData)))
                .setFields("id, parents")
                .execute();
        return "https://drive.google.com/uc?id="+file.getId();
    }

    public void grantAuthority(String sheetId,Sheets sheetService){
        JsonBatchCallback<Permission> callback = new JsonBatchCallback<Permission>() {
            @Override
            public void onFailure(GoogleJsonError e,
                                  HttpHeaders responseHeaders)
                    throws IOException {
                // Handle error
                System.err.println(e.getMessage());
            }

            @Override
            public void onSuccess(Permission permission,
                                  HttpHeaders responseHeaders)
                    throws IOException {
                System.out.println("Permission ID: " + permission.getId());
            }
        };
        Drive drive=null;
        try {
             drive= getDriveService();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BatchRequest batch = drive.batch();
        Permission userPermission = new Permission()
                .setType("user")
                .setRole("writer")
                .setEmailAddress("vermasahil269@gmail.com");
        try {
            drive.permissions().create(sheetId, userPermission)
                    .setFields("id")
                    .queue(batch, callback);
            batch.execute();
            System.out.println("hari bol..!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
