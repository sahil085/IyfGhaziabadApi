package com.IyfGZB.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SendSms{

    public static void main(String[] args)
    {
        //Your user name
        String username = "iyfgzb";
        //Your authentication key
        String authkey = "09151ef124XX";
        //Multiple mobiles numbers separated by comma (max 200)
        String mobiles = "8859167442,8920041231,9540941141,9958473800,8188810855,9958107164,9953254461,9650444899,9873571438,7836868180,9917015608,9557535457,8505813574,8527224790,9318376406,8920339943,9044587172,8375818918,9891244832,8439320495,9140059401,9871936490,9634393134,8929909125,7084189406,9582119777,9654630010,9555437643,9899365225,9675775352,9997195822,7417713702,9582192526,9213159811,7838093078,9717237983,9582521952,7352299931,9667709060,9953102233,7800883909,8130039731,9999426976,8076217354,7503362867,9911724304,8285885513,7017318837,8802333419,9315877760,8750039650,8826118697,9205282061,7836852437,7906486619,9821279570,8447055886,9871918929,7250854643,9631692926,7065545272,9711172193,9878978159,8799779694,7309565601,9412592252,9643163521,7701975670,8010279221,8576972122,7800268987,9953446554,8010322695,8077668355,7376341488,7752980164,8810402293,7838047064,8743960331,9540482777,9811098834,7678185850,9599803013,8130587752,9999601540,9990773991,9911182907,9818026595,8375057112,9818140423,9212884005,9650053563,8368284241,9818436985,7887033335,9013268098,9911438838,8859734525,8410618965,8707083612,7017163925,9315168866,7317379243,8505830255,8954302019,8837578856,8826119608,9540614722,7827487010,9870874257,9560686441,9559971280,9013664292,9758520597,7065732247,9929255265,8394909735,9639176124,9627595050,,8707027697,8279874712,7302808636,9455390022,7007912346,8750352103,7536093608,8650995794,6387680322,8787011321,8826344218,8860405038,9410616913,9582014512,7503341928,8077826117,8574747427,8505852591,7065469636,9654887596,8171733679,9557480337,7042610770,9667531996,8923745739,9871993481,9999625999,9999380205,7678333690,6388468605,9910528338,8840183202,7065697013";
        //Sender ID,While using route4 sender id should be 6 characters long.
        String senderId = "INFOSM";
        //Your message to send, Add URL encoding here.

        String message = "ISKCON Youth Forum happily invites you for our weekly Youth Empowerment program. \n" +
                "\n" +
                "Topic - Retentivity of Mind\n" +
                "\n" +
                "By Gajendra Nath Sir \n" +
                "M.tech from IIT Kanpur and also had a working experience with BHEL. \n" +
                "\n" +
                "Features:\n" +
                "\n" +
                "Heart transforming session from the timeless wisdom of vedic literature and with delicious sumptuous meal\n" +
                "\n" +
                "Venue: ISKCON Ghaziabad Seminar Hall\n" +
                "\n" +
                "Timings: 4:30 - 6:00 PM\n" +
                "\n" +
                "For more details:\n" +
                "\n" +
                "8375818918\n" +
                "8920041231";
        //define route
        String accusage="1";

        //Prepare Url
        URLConnection myURLConnection=null;
        URL myURL=null;
        BufferedReader reader=null;

        //encoding message
        String encoded_message=URLEncoder.encode(message);

        //Send SMS API
        String mainUrl="http://sms.ontimeinfotech.com/submitsms.jsp?";

        //Prepare parameter string
        StringBuilder sbPostData= new StringBuilder(mainUrl);
        sbPostData.append("user="+username);
        sbPostData.append("&key="+authkey);
        sbPostData.append("&mobile="+mobiles);
        sbPostData.append("&message="+encoded_message);
        sbPostData.append("&accusage="+accusage);
        sbPostData.append("&senderid="+senderId);

        //final string
        mainUrl = sbPostData.toString();
        try
        {
            //prepare connection
            myURL = new URL(mainUrl);
            myURLConnection = myURL.openConnection();
            myURLConnection.connect();
            reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
            //reading response
            String response;
            while ((response = reader.readLine()) != null)
                //print response
                System.out.println(response);

            //finally close connection
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
