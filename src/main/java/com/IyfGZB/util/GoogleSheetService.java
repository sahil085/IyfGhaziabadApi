package com.IyfGZB.util;

import com.IyfGZB.dto.SeminarAttendanceDTO;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.Permission;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by sahil on 4/8/18.
 */
public class GoogleSheetService {

    private Sheets sheetService;


    public void getSheetService() throws GeneralSecurityException,
            IOException {
        InputStream input = new URL("https://github.com/sahil085/IyfGhaziabadApi/blob/JuneQa/Iskcongzb-0f45852524c6.p12?raw=true").openStream();
        URL url = new URL("http://github.com/sahil085/IyfGhaziabadApi/blob/JuneQa/Iskcongzb-0f45852524c6.p12");
        java.io.File key = new java.io.File("Iskcongzb-0f45852524c6.p12");

        if (!key.exists()) {
            OutputStream out = new FileOutputStream(key);
            byte[] buf = new byte[1024];
            int len;
            while ((len = input.read(buf)) > 0)
                out.write(buf, 0, len);
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

        sheetService = new Sheets.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("INSERT_YOUR_APPLICATION_NAME")
                .build();
        writeDataInSheet(null,"Shree Kripalu Ji maharaj ki jai");

    }
    public String createSpreadSheet(String title) throws IOException {
        Sheets service = null;
            service = this.sheetService;

        // [START sheets_create]
        Spreadsheet spreadsheet = new Spreadsheet()
                .setProperties(new SpreadsheetProperties()
                        .setTitle(title));
        spreadsheet = service.spreadsheets().create(spreadsheet)
                .setFields("spreadsheetId")
                .execute();
        System.out.println("Spreadsheet ID: " + spreadsheet.getSpreadsheetId());
        // [END sheets_create]
        return spreadsheet.getSpreadsheetId();
    }

    public void writeDataInSheet(List<Object> myData,String title) {

        try {
            String id = createSpreadSheet(title);
            String writeRange ="Sheet1!E5";

            List<SeminarAttendanceDTO> attendanceDTOList = new ArrayList<>();
            for(int i=0 ; i<5;i++){
                SeminarAttendanceDTO attendanceDTO = new SeminarAttendanceDTO();
                attendanceDTO.setSeminarTitle("hare krishna");
                attendanceDTO.setSpeakerName("HG Gopal Krishna Goswami Maharaj");
                attendanceDTO.setStatus("Present");
                attendanceDTO.setUserName("sahil verma");
                attendanceDTOList.add(attendanceDTO);
            }



            List<List<Object>> writeData = new ArrayList<>();

            for (SeminarAttendanceDTO someData : attendanceDTOList) {
                List<Object> dataRow = new ArrayList<>();
                dataRow.add(someData.getSeminarTitle());
                dataRow.add(someData.getSpeakerName());
                dataRow.add(someData.getDate());
                dataRow.add(someData.getUserName());
                dataRow.add(someData.getStatus());
                writeData.add(dataRow);
            }
//            ValueRange vr = new ValueRange().setValues(writeData).setMajorDimension("ROWS");
            List<ValueRange> data = new ArrayList<>();
            data.add(new ValueRange()
                    .setRange("A2")
                    .setValues(writeData));
            data.add(new ValueRange()
                    .setRange("A1")
                    .setValues(Arrays.asList(
                            Arrays.asList("<center><b>Title</b></center>",
                                    "<center><b>Speaker Name</b></center>",
                                    "<center><b>User Name</b></center>",
                                    "<center><b>status</b></center>"))));
//
            BatchUpdateValuesRequest batchBody = new BatchUpdateValuesRequest()
                    .setValueInputOption("RAW")
                    .setData(data);
//
            BatchUpdateValuesResponse batchResult = this.sheetService.spreadsheets().values()
                    .batchUpdate(id, batchBody)
                    .execute();

//            this.sheetService.spreadsheets().values()
//                    .update(id, writeRange, data)
//                    .setValueInputOption("RAW")
//                    .execute();
            GoogleDriveService googleDriveService= new GoogleDriveService();
            googleDriveService.grantAuthority(id,this.sheetService);
            }
        catch (Exception e) {
          e.printStackTrace();
            // handle exception
        }
    }

    public static void main(String[] args) {

        GoogleSheetService sheetService= new GoogleSheetService();
        try {
            sheetService.getSheetService();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
