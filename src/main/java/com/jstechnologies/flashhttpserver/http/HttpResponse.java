package com.jstechnologies.flashhttpserver.http;

import java.nio.charset.StandardCharsets;

public class HttpResponse extends HttpMessage{

    private int statusCode;
    private String statusMessage;
    private String body;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public byte[] buildResponse(){

        String CLRF="\n";
        String response="HTTP/1.1 "+statusCode+" "+statusMessage+CLRF;
        if(body!=null)
            response+="Content-Length: "+body.getBytes().length+CLRF+CLRF
                    +body+CLRF+CLRF;
        return (response.getBytes(StandardCharsets.US_ASCII));
    }
}
