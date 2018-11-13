package com.IyfGZB.util;

import com.IyfGZB.constants.ClassLevel;
import com.IyfGZB.domain.Seminar;
import com.IyfGZB.domain.SeminarAttendance;
import com.IyfGZB.domain.User;
import com.IyfGZB.domain.UserInfo;
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
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by sahil on 4/8/18.
 */
@Service
public class UserExcelSheetMaker {

    private Sheets sheetService;


    public void getSheetService(List<UserInfo> userInfoList) throws GeneralSecurityException,
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
        writeDataInSheet(userInfoList);
    }
    public String createSpreadSheet() throws IOException {
        Sheets service = null;
        service = this.sheetService;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
       String date = simpleDateFormat.format(new Date());

        // [START sheets_create]
        Spreadsheet spreadsheet = new Spreadsheet()
                .setProperties(new SpreadsheetProperties()
                        .setTitle("User List ( "+date+" )"));
        spreadsheet = service.spreadsheets().create(spreadsheet)
                .setFields("spreadsheetId")
                .execute();
        System.out.println("Spreadsheet ID: " + spreadsheet.getSpreadsheetId());
        // [END sheets_create]
        return spreadsheet.getSpreadsheetId();
    }

    public void writeDataInSheet(List<UserInfo> userInfoList) {

        try {
            String id = createSpreadSheet();
            String writeRange ="Sheet1!E5";




            List<List<Object>> writeData = new ArrayList<>();

            for (UserInfo userInfo : userInfoList) {
                List<Object> dataRow = new ArrayList<>();
                dataRow.add(userInfo.getUsername());
                dataRow.add(userInfo.getMobileNumber());
                dataRow.add(userInfo.getEmail());
                dataRow.add(userInfo.getClassLevel());
                writeData.add(dataRow);
            }
//            ValueRange vr = new ValueRange().setValues(writeData).setMajorDimension("ROWS");
            List<ValueRange> data = new ArrayList<>();
            data.add(new ValueRange()
                    .setRange("A2")
                    .setValues(writeData));
//
            BatchUpdateValuesRequest batchBody = new BatchUpdateValuesRequest()
                    .setValueInputOption("RAW")
                    .setData(data);


            BatchUpdateValuesResponse batchResult = this.sheetService.spreadsheets().values()
                    .batchUpdate(id, batchBody)
                    .execute();
            BatchUpdateSpreadsheetRequest body = new BatchUpdateSpreadsheetRequest().setRequests(getRequests());

            BatchUpdateSpreadsheetResponse response = this.sheetService.spreadsheets().batchUpdate(id, body).execute();


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

    // Make Header Of Excel Sheet
    public List<Request> getRequests(){
        List<Request> requests = new ArrayList<>();
        requests.add(new Request().setUpdateDimensionProperties(
                new UpdateDimensionPropertiesRequest()
                        .setRange(
                                new DimensionRange()
                                        .setDimension("COLUMNS")
                                        .setStartIndex(0).setEndIndex(4)
                        )

                        .setProperties(new DimensionProperties().setPixelSize(250)).setFields("pixelSize")));

        requests.add(new Request()
                .setRepeatCell(new RepeatCellRequest()
                        .setCell(new CellData()
                                .setUserEnteredValue( new ExtendedValue().setStringValue("User Name"))
                                .setUserEnteredFormat(new CellFormat().
                                        setHorizontalAlignment("CENTER")
                                        .setTextFormat(new TextFormat()
                                                .setFontSize(15)
                                                .setBold(Boolean.TRUE)
                                        )
                                )
                        )
                        .setRange(new GridRange()
                                .setStartRowIndex(0)
                                .setEndRowIndex(1)
                                .setStartColumnIndex(0)
                                .setEndColumnIndex(1)
                        )
                        .setFields("*")
                )
        );
        requests.add(new Request()
                .setRepeatCell(new RepeatCellRequest()
                        .setCell(new CellData()
                                .setUserEnteredValue( new ExtendedValue().setStringValue("Contact Number"))
                                .setUserEnteredFormat(new CellFormat()
                                        .setHorizontalAlignment("CENTER")
                                        .setTextFormat(new TextFormat()
                                                .setFontSize(15)
                                                .setBold(Boolean.TRUE)
                                        )
                                )
                        )
                        .setRange(new GridRange()

                                .setStartRowIndex(0)
                                .setEndRowIndex(1)
                                .setStartColumnIndex(1)
                                .setEndColumnIndex(2)
                        )
                        .setFields("*")
                )
        );
        requests.add(new Request()
                .setRepeatCell(new RepeatCellRequest()
                        .setCell(new CellData()
                                .setUserEnteredValue( new ExtendedValue().setStringValue("Email Id"))
                                .setUserEnteredFormat(new CellFormat()
                                        .setHorizontalAlignment("CENTER")
                                        .setTextFormat(new TextFormat()
                                                .setFontSize(15)
                                                .setBold(Boolean.TRUE)
                                        )
                                )
                        )
                        .setRange(new GridRange()
                                .setStartRowIndex(0)
                                .setEndRowIndex(1)
                                .setStartColumnIndex(2)
                                .setEndColumnIndex(3)
                        )
                        .setFields("*")
                )
        );
        requests.add(new Request()
                .setRepeatCell(new RepeatCellRequest()
                        .setCell(new CellData()
                                .setUserEnteredValue( new ExtendedValue().setStringValue("Class Level"))
                                .setUserEnteredFormat(new CellFormat()
                                        .setHorizontalAlignment("CENTER")
                                        .setTextFormat(new TextFormat()
                                                .setFontSize(15)
                                                .setBold(Boolean.TRUE)
                                        )
                                )
                        )
                        .setRange(new GridRange()
                                .setStartRowIndex(0)
                                .setEndRowIndex(1)
                                .setStartColumnIndex(3)
                                .setEndColumnIndex(4)
                        )
                        .setFields("*")
                )
        );
        return requests;
    }
    public void sendUserListExcelSheet(List<UserInfo> userInfoList){
        try {
            getSheetService(userInfoList);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//    public static void main(String[] args) {
//
//        GoogleSheetService sheetService= new GoogleSheetService();
//        try {
//            sheetService.getSheetService();
//        } catch (GeneralSecurityException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}
