package com.jstechnologies.flashhttpserver.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

import static com.jstechnologies.flashhttpserver.http.Constants.CR;
import static com.jstechnologies.flashhttpserver.http.Constants.LF;

public class HttpParser {

    private boolean strict=true;
    public HttpParser(){

    }

    public HttpRequest parse(InputStream inputStream) throws Exception{

        InputStreamReader inputStreamReader= new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
        BufferedReader reader=new BufferedReader(inputStreamReader);
        HttpRequest request= new HttpRequest();

        //begin parsing request
        parseRequestLine(reader,request);
        parseHeaders(reader,request);

        //SKip parsing Body for GET Request
        if(!request.getMethod().equals("GET")){
            parseBody(reader,request);
        }

        return request;
    }

    private void parseBody(BufferedReader reader,HttpRequest request) throws IOException {
        int _byte;

        while((_byte=reader.read())>-1){
            request.addToBody((byte)_byte);
        }

    }

    private void parseHeaders(BufferedReader reader,HttpRequest request)  throws IOException {
        String line=null;
        while((line=reader.readLine())!=null){

            if(line.isEmpty())
                return;
            //split headers into key value pairs
            String[] pairs=line.split(":",2);
            if(pairs[0]!=null && pairs[1]!=null)
                request.addHeader(pairs[0],pairs[1]);

        }

    }

    private void parseRequestLine(BufferedReader reader,HttpRequest request) throws Exception {

        String line;
        while ((line = reader.readLine())!=null) {

            try{
                if(line==null || line.isEmpty())
                    return;
                String[] parsed=line.split(" ");
                if(parsed.length!=3)
                    throw new Exception("Invalid Request Line");
                request.setMethod(parsed[0]);
                request.setTarget(parsed[1]);
                request.setVersion(parsed[2]);
                return;
            }catch (Exception e){
                throw new IOException("BAD request");
            }

        }

    }

    boolean isCRLF(int currentByte,InputStreamReader reader) throws IOException {
        int nextByte=reader.read();
        if(currentByte==CR && nextByte==LF){
            return true;
        }
        else if(currentByte==CR){
            throw new IOException("Malformed request");
        }
        return false;
    }


    void print(Object obj){
        System.out.println(obj);
    }
}
