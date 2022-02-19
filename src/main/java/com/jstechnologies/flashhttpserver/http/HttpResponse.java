package com.jstechnologies.flashhttpserver.http;

import com.google.common.net.MediaType;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.IntFunction;


public class HttpResponse extends HttpMessage{

    private int statusCode;
    private String statusMessage;
    private byte[] body;
    private ArrayList<Byte> buildableResponse;
    private Map<String,String> headers;
    ContentType contentType;

    public HttpResponse() {
        this.headers = new HashMap<>();
        buildableResponse=new ArrayList();
    }

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

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
        this.addHeader("Content-Length", body.length+"");
    }

    public void setContentType(ContentType contentType){
        this.addHeader("Content-Type",contentType.getType());
    }

    private void addHeader(String type,String value){
        this.headers.put(type,value);
    }

    private boolean push(byte[] snippet){
        for(byte b:snippet){
            buildableResponse.add(b);
        }
        return true;
    }

    private boolean push(String snippet){
        return push(snippet.getBytes(StandardCharsets.UTF_8));
    }



    public byte[] buildResponse(){
        String CLRF="\n";
        String response="HTTP/1.1 "+statusCode+" "+statusMessage+CLRF;
        for(Map.Entry<String,String> header:this.headers.entrySet()){
            response+=header.getKey()+": "+header.getValue()+CLRF;
        }
        response+=CLRF;
        push(response);
        push(body);
        push(CLRF+CLRF);

        byte[] bytes=new byte[buildableResponse.size()];
        int i=0;
        for(Byte b:buildableResponse){
            bytes[i++]=b;
        }
        return bytes;
    }
}
