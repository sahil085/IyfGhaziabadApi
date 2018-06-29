package com.IyfGZB.CourseDTO;


import org.springframework.stereotype.Component;

public class CommonResponseDTO {

String response;
String type;

public CommonResponseDTO(String type,String response)
{
    this.response=response;
    this.type=type;
}

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
