package com.jstechnologies.flashhttpserver.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequest extends HttpMessage{
    private String method;
    private String target;
    private String version;
    private Map<String,String> headers;
    private List<Byte> body;

    HttpRequest(){
        this.headers=new HashMap<>();
        this.body= new ArrayList<>();
    }

    public String getMethod() {
        return method;
    }

    void setMethod(String method) {
        this.method = method;
    }

    public String getTarget() {
        return target;
    }

    public byte[] getBody() {

        byte[] result=new byte[body.size()];
        for(int i=0;i<body.size();i++){
            result[i]=body.get(i);
        }
        return result;
    }

    boolean addToBody(byte _byte){
        return this.body.add(_byte);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    String addHeader(String headerKey, String headerValue){
        return this.headers.put(headerKey,headerValue);
    }

    void setTarget(String target) {
        this.target = target;
    }

    public String getVersion() {
        return version;
    }

    void setVersion(String version) {
        this.version = version;
    }
}
